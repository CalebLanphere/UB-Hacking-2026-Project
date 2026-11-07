package com.TeamYellow;

import java.io.File;

public class ResourcePaths {
    private static final String SEPARATOR = File.separator;
    private static final String WORKING_DIRECTORY = System.getenv("PWD") + SEPARATOR;

    public static final String APP_DIRECTORY = WORKING_DIRECTORY + "App" + SEPARATOR;

    public static final String FONT_DIRECTORY = APP_DIRECTORY + "Fonts" + SEPARATOR;
}
