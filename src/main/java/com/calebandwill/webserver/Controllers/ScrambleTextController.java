package com.calebandwill.webserver.Controllers;

import com.calebandwill.webserver.TextScrambleMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/scramble")
public class ScrambleTextController {
    private TextScrambleMapping scrambleMapping;

    public ScrambleTextController(TextScrambleMapping scrambleMapping) {
        this.scrambleMapping = scrambleMapping;
    }

    @PutMapping("/upload-text")
    public ResponseEntity<String> uploadTextToScramble(@RequestParam(required = true) String textToScramble) {
        Logger.getGlobal().info(textToScramble);
        return null;
    }

}
