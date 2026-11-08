package com.calebandwill.webserver.TextObfuscation;

import com.calebandwill.webserver.Paths.ResourcePaths;
import com.calebandwill.webserver.Services.TextObfuscationScrambleService;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import org.springframework.stereotype.Component;
import tools.jackson.core.json.JsonFactoryBuilder;

import java.io.IOException;
import java.util.HashMap;

@Component
public class TextObfuscationService {
    private static TextObfuscationScrambleService service;

//    public TextObfuscationService(TextObfuscationScrambleService service) {
//        TextObfuscationService.service = service;
//    }

    /**
     * Creates a modified font based off an existing font
     * @param fontFileName The filename of the font to modify
     * @throws IOException If the file cannot be found or executed
     */
    public static void generateFont(String fontFileName) throws IOException {
        HashMap<Character, Character> charsToChange = CharRandomizer.scrambler();
        JsonObjectBuilder builder = Json.createObjectBuilder();

        for(Character characterToChange : charsToChange.keySet()) {
            builder.add(String.valueOf(charsToChange.get(characterToChange)), characterToChange);
        }

        System.out.println(builder.build());


        // Python runner is so we can use the FontTools library to modify fonts
        PythonRunner.modifyTrueTypeFont(charsToChange,
                ResourcePaths.FONT_DIRECTORY + fontFileName,
                ResourcePaths.FONT_DIRECTORY + "output.ttf");
    }
}