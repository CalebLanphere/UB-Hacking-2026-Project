package com.calebandwill.webserver.ImageWatermarker;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class ImageManipulator {



    public static final int TARGET_WIDTH = 1920;
    public static final int TARGET_HEIGHT = 1080;
    public static final String WATERMARK = "Scrambl";

    private ImageManipulator() {}



    /**
     * Full pipeline: read upload -> resize -> embed watermark -> PNG bytes.
     * In a controller: processUpload(file.getInputStream())
     */
    public static byte[] processUpload(InputStream uploadedImage) throws IOException {
        BufferedImage original = ImageIO.read(uploadedImage);
        if (original == null) {
            throw new IOException("Uploaded file is not a supported image format");
        }

        BufferedImage resized = resize(original, TARGET_WIDTH, TARGET_HEIGHT);
        embed(resized, WATERMARK);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (!ImageIO.write(resized, "png", out)) {
            throw new IOException("No PNG writer available");
        }
        return out.toByteArray();
    }

    /** Scales to exactly width x height (stretches if aspect ratio differs). */
    static BufferedImage resize(BufferedImage src, int width, int height) {
        // TYPE_INT_RGB: fixed 8-bit-per-channel layout, no alpha/palette surprises,
        // so getRGB/setRGB round-trip exactly and LSBs are not altered.
        BufferedImage dst = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = dst.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(src, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return dst;
    }

    /**
     * Builds the payload (4-byte big-endian length + UTF-8 message) and tiles it
     * repeatedly over every color channel of every pixel, one bit per channel
     * (R, G, B in row-major order), most significant bit of each byte first.
     * The last copy is simply truncated where the image runs out. Modifies the
     * image in place.
     */
    static void embed(BufferedImage img, String message) {
        byte[] msg = message.getBytes(StandardCharsets.UTF_8);
        byte[] payload = new byte[4 + msg.length];
        payload[0] = (byte) (msg.length >>> 24);
        payload[1] = (byte) (msg.length >>> 16);
        payload[2] = (byte) (msg.length >>> 8);
        payload[3] = (byte) msg.length;
        System.arraycopy(msg, 0, payload, 4, msg.length);

        int width = img.getWidth();
        int height = img.getHeight();
        int payloadBits = payload.length * 8;
        long capacity = (long) width * height * 3;
        if (payloadBits > capacity) {
            throw new IllegalArgumentException("Message too large for image");
        }

        int[] px = img.getRGB(0, 0, width, height, null, 0, width);
        int j = 0; // position within the current copy of the payload
        for (int p = 0; p < px.length; p++) {
            int rgb = px[p];
            for (int shift = 16; shift >= 0; shift -= 8) { // R, G, B
                int bit = (payload[j >> 3] >> (7 - (j & 7))) & 1;
                rgb = (rgb & ~(1 << shift)) | (bit << shift);
                if (++j == payloadBits) {
                    j = 0;
                }
            }
            px[p] = rgb;
        }
        img.setRGB(0, 0, width, height, px, 0, width);
    }

    /** Reads a watermark back out. Handy for tests and verification. */
    public static String extract(InputStream pngStream) throws IOException {
        BufferedImage img = ImageIO.read(pngStream);
        if (img == null) {
            throw new IOException("Not a supported image format");
        }
        return extract(img);
    }

    /**
     * Reads the length from the first copy, then majority-votes each payload bit
     * across every copy in the image, so a damaged region doesn't break extraction.
     */
    static String extract(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        long capacity = (long) width * height * 3;
        int[] px = img.getRGB(0, 0, width, height, null, 0, width);

        // Length header from the first 32 bits.
        int length = 0;
        for (int i = 0; i < 32; i++) {
            length = (length << 1) | lsb(px, i);
        }
        if (length < 0 || ((long) length + 4) * 8 > capacity) {
            throw new IllegalStateException("No valid watermark found");
        }

        int payloadBits = (length + 4) * 8;
        int[] ones = new int[payloadBits];
        int[] total = new int[payloadBits];
        int j = 0;
        for (long i = 0; i < capacity; i++) {
            ones[j] += lsb(px, i);
            total[j]++;
            if (++j == payloadBits) {
                j = 0;
            }
        }

        byte[] msg = new byte[length];
        for (int i = 0; i < length * 8; i++) {
            int k = 32 + i;
            int bit = ones[k] * 2 > total[k] ? 1 : 0;
            msg[i >> 3] |= (byte) (bit << (7 - (i & 7)));
        }
        return new String(msg, StandardCharsets.UTF_8);
    }

    private static int lsb(int[] px, long bitIndex) {
        int rgb = px[(int) (bitIndex / 3)];
        int shift = 16 - (int) (bitIndex % 3) * 8;
        return (rgb >> shift) & 1;
    }

}
