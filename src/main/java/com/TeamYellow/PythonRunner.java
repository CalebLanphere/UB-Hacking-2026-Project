package com.TeamYellow;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.Source;
import java.io.File;
import java.io.IOException;

import java.util.HashMap;

public class PythonRunner {
    public static void modifyTrueTypeFont(HashMap<Character, Character> mapOfChars, String fileInput) throws Exception {
        try (Context context = Context.newBuilder("python")
                .allowAllAccess(true)
                .build()) {

            // Create a Python dictionary
            Value dict = context.eval("python", "{}");

            // Populate dictionary with character mappings
            for (Character key : mapOfChars.keySet()) {
                String charToReplace = key.toString();
                String charToReplaceWith = mapOfChars.get(key).toString();

                dict.putHashEntry(charToReplace, charToReplaceWith);
            }

            // Set the Python variable "mappings"
            context.getBindings("python").putMember("mappings", dict);

            // Set the Python variable "mappings"
            context.getBindings("python").putMember("input_path", fileInput);

            // Execute the Python script
            Source source = Source.newBuilder(
                    "python",
                    new File("src/main/resources/FontRemapper.py")
            ).build();

            context.eval(source);
        }

    }
}
