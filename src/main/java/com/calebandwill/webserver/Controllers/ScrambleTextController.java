package com.calebandwill.webserver.Controllers;

import com.calebandwill.webserver.Paths.ResourcePaths;
import com.calebandwill.webserver.Services.TextObfuscationScrambleService;
import com.calebandwill.webserver.TextObfuscation.TextObfuscationService;
import com.calebandwill.webserver.TextScrambleMapping;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/scramble")
public class ScrambleTextController {
    private TextObfuscationScrambleService service;

    public ScrambleTextController(TextObfuscationScrambleService service) {
        this.service = service;
    }

    @PutMapping("/upload-auction")
    public ResponseEntity<String> uploadAuction(@RequestParam(required = true) String bidID,
                                                @RequestParam(required = true) String itemName,
                                                @RequestParam(required = true) String itemDesc,
                                                @RequestParam(required = true) String sellerName,
                                                @RequestParam(required = false) String currBid,
                                                @RequestParam(required = false) String currBidBy) {
        service.createAuction(Integer.parseInt(bidID), itemName, itemDesc, sellerName);

        return null;
    }

    @GetMapping("/auction")
    public JsonObject uploadAuction(@RequestParam(required = true) String bidID) {
        JsonObject auction = service.getAuction(Integer.parseInt(bidID));
        JsonObjectBuilder builder = Json.createObjectBuilder();

        for(String key : auction.keySet()) {
            if(!key.equals("bidId")) {
                builder.add(key, Json.createValue(scramble(auction.getString(key))));
            } else {
                builder.add(key, Json.createValue(auction.getString(key)));
            }
        }

        return builder.build();
    }

    @PostMapping("/regenerate-scramble")
    public ResponseEntity<String> regenerateSrambler(@RequestParam MultipartFile font) {
        try {
            Logger.getGlobal().info(font.getName() + " | " + font.getOriginalFilename());
            if(Objects.requireNonNull(font.getOriginalFilename()).indexOf('.') != -1) {
                String fileType = font.getOriginalFilename().substring(font.getOriginalFilename().indexOf('.'));
                if(!fileType.equals(".ttf")) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
            String fileName = font.getOriginalFilename();
            font.transferTo(Path.of(ResourcePaths.FONT_DIRECTORY + fileName));

            service.placeMapInDb(TextObfuscationService.generateFont(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    private String scramble(String textToScramble) {
        Logger.getGlobal().info(textToScramble);
        char[] arrOfScrambledText = new char[textToScramble.length()];
        char[] originalCharArray = textToScramble.toCharArray();

        if(TextScrambleMapping.mapOfCharactersScrambled == null) {
            TextScrambleMapping.mapOfCharactersScrambled = service.getMapOfCharacters();
        }

        for(int i = 0; i < textToScramble.length(); i++) {
            if(TextScrambleMapping.mapOfCharactersScrambled.get(originalCharArray[i]) == null) {
                arrOfScrambledText[i] = originalCharArray[i];
            } else {
                arrOfScrambledText[i] = TextScrambleMapping.mapOfCharactersScrambled.get(originalCharArray[i]);
            }
        }

        Logger.getGlobal().info(new String(arrOfScrambledText));

        return new String(arrOfScrambledText);
    }

}
