package com.calebandwill.webserver.TextObfuscation;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PythonRunner {
    /**
     * Modifies a TrueTypeFont (.ttf) with a Python script
     * @param mapOfChars A HashMap of characters that the font will change
     * @param fileInput The .ttf font to modify
     * @param fileOutput Where to put and what to call the modified font
     * @throws IOException If the Python script cannot be executed or the file cannot be found
     */
    public static void modifyTrueTypeFont(HashMap<Character, Character> mapOfChars,
                                          String fileInput,
                                          String fileOutput) throws IOException {

        // Tries to create a Python3 Interpreter
        try (Context context = Context.newBuilder("python")
                .allowAllAccess(true)
                .build()) {

            // Creates a Python dictionary
            Value dict = context.eval("python", "{}");

            // Populate dictionary with character mappings
            for (Character key : mapOfChars.keySet()) {
                String charToReplace = key.toString();
                String charToReplaceWith = mapOfChars.get(key).toString();

                dict.putHashEntry(charToReplace, charToReplaceWith);
            }

            // Creates a Python variable "mappings"
            context.getBindings("python").putMember("mappings", dict);

            // Creates a Python variable "input_path"
            context.getBindings("python").putMember("input_path", fileInput);

            // Creates a Python variable "output_path"
            context.getBindings("python").putMember("output_path", fileOutput);

            // Creates a builder to find the Python script
            Source source = Source.newBuilder(
                    "python",
                    new File("src/main/resources/FontRemapper.py")
            ).build();

            // Runs the Python script
            context.eval(source);
        }

    }
}
