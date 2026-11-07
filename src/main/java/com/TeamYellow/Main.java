package com.TeamYellow;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            HashMap<Character, Character> charsToChange = new HashMap<Character, Character>();
            charsToChange.put('A', 'C');
            charsToChange.put('a', 'c');

            PythonRunner.modifyTrueTypeFont(charsToChange);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}