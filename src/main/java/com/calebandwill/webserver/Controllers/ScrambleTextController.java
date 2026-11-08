package com.calebandwill.webserver.Controllers;

import com.calebandwill.webserver.Services.TextObfuscationScrambleService;
import com.calebandwill.webserver.TextObfuscation.TextObfuscationService;
import com.calebandwill.webserver.TextScrambleMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/scramble")
public class ScrambleTextController {
    private TextScrambleMapping scrambleMapping;
    private TextObfuscationScrambleService service;

    public ScrambleTextController(TextScrambleMapping scrambleMapping, TextObfuscationScrambleService service) {
        this.scrambleMapping = scrambleMapping;
        this.service = service;
    }

    @PutMapping("/upload-auction")
    public ResponseEntity<String> uploadTextToScramble(@RequestParam(required = true) String textToScramble) {


        return null;
    }

    @PostMapping("/regenerate-scramble")
    public ResponseEntity<String> regenerateSrambler() {
        try {
            service.placeMapInDb(TextObfuscationService.generateFont("MontserratBold-DOWZd.ttf"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    private void scramble(String textToScramble) {
        Logger.getGlobal().info(textToScramble);
        char[] arrOfScrambledText = new char[textToScramble.length()];
        char[] originalCharArray = textToScramble.toCharArray();

        for(int i = 0; i < textToScramble.length(); i++) {
            arrOfScrambledText[i] = scrambleMapping.mapOfCharactersScrambled.get(originalCharArray[i]);
        }

        Logger.getGlobal().info(new String(arrOfScrambledText));
    }

}
