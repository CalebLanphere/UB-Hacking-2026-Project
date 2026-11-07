package com.TeamYellow;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        try {
            Setup.setupDirectories();

            PythonRunner.modifyTrueTypeFont(charsToChange,  ResourcePaths.FONT_DIRECTORY + "MontserratBold-DOWZd.ttf", ResourcePaths.FONT_DIRECTORY + "output.ttf");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}