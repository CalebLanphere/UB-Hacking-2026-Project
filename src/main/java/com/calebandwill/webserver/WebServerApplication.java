package com.calebandwill.webserver;

import com.calebandwill.webserver.Exceptions.SetupFailureException;
import com.calebandwill.webserver.TextObfuscation.Setup;
import com.calebandwill.webserver.TextObfuscation.TextObfuscationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.util.logging.Logger;

@SpringBootApplication
public class WebServerApplication {

    public static void main(String[] args) {
        try {
            // Set up the relative directories the application requires
            Setup.setupDirectories();
        } catch (SetupFailureException exception) {
            // If it cannot create the directories, FileNotFoundExceptions will be thrown internally
            Logger.getGlobal().severe("Application Failed To Create Required Directories! " + exception.getMessage());
            System.exit(1);
        }

        try {
            TextObfuscationService.generateFont("MontserratBold-DOWZd.ttf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Starts Spring Boot application
        SpringApplication.run(WebServerApplication.class, args);
    }

}
