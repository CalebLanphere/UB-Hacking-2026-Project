package com.calebandwill.webserver.TextObfuscation;

import com.calebandwill.webserver.Exceptions.SetupFailureException;
import com.calebandwill.webserver.Paths.ResourcePaths;

import java.io.File;

public class Setup {

    public static void setupDirectories() {
        // Sets up a file reference to the directory listed | PWD/App/
        File dir = new File(ResourcePaths.APP_DIRECTORY);

        try {
            // If the directory doesn't exist, create the directory
            if (!dir.exists()) {
                dir.mkdir();
            }
        } catch (SecurityException securityException) {
            // If the directory cannot be created due to a security rule, throw a SetupFailure
            throw new SetupFailureException("Failed to create directory '/App': "
                    + securityException.getMessage());
        }

        // Sets up a file reference to the directory listed | PWD/App/Fonts
        dir = new File(ResourcePaths.FONT_DIRECTORY);

        try {
            // If the directory doesn't exist, create the directory
            if (!dir.exists()) {
                dir.mkdir();
            }
        } catch (SecurityException securityException) {
            // If the directory cannot be created due to a security rule, throw a SetupFailure
            throw new SetupFailureException("Failed to create directory '/App/Fonts': "
                    + securityException.getMessage());
        }

        // Sets up a file reference to the directory listed | PWD/App/Photos
        dir = new File(ResourcePaths.PHOTO_DIRECTORY);

        try {
            // If the directory doesn't exist, create the directory
            if (!dir.exists()) {
                dir.mkdir();
            }
        } catch (SecurityException securityException) {
            // If the directory cannot be created due to a security rule, throw a SetupFailure
            throw new SetupFailureException("Failed to create directory '/App/Photos': "
                    + securityException.getMessage());
        }
    }
}
