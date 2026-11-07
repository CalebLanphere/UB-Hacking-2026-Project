package com.TeamYellow;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        try {
            Setup.setupDirectories();

            HashMap<Character, Character> charsToChange = new HashMap<Character, Character>();
            charsToChange.put('A', 'C');
            charsToChange.put('a', 'c');

            PythonRunner.modifyTrueTypeFont(charsToChange,  ResourcePaths.FONT_DIRECTORY + "MontserratBold-DOWZd.ttf", ResourcePaths.FONT_DIRECTORY + "output.ttf");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}