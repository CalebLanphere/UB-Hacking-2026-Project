package com.calebandwill.webserver.TextObfuscation;

import com.calebandwill.webserver.Paths.ResourcePaths;
import java.io.IOException;
import java.util.HashMap;

public class TextObfuscationService {

    /**
     * Creates a modified font based off an existing font
     * @param fontFileName The filename of the font to modify
     * @throws IOException If the file cannot be found or executed
     */
    public static void generateFont(String fontFileName) throws IOException {
        HashMap<Character, Character> charsToChange = CharRandomizer.scrambler();

        // Python runner is so we can use the FontTools library to modify fonts
        PythonRunner.modifyTrueTypeFont(charsToChange,
                ResourcePaths.FONT_DIRECTORY + fontFileName,
                ResourcePaths.FONT_DIRECTORY + "output.ttf");
    }
}