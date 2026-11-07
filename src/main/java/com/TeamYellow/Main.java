package com.TeamYellow;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        try {
            HashMap<Character, Character> charsToChange = CharRandomizer.scrambler();

            PythonRunner.modifyTrueTypeFont(charsToChange);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}