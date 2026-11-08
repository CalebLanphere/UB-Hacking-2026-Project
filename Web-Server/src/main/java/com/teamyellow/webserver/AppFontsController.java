package com.teamyellow.webserver;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/App/Fonts")
public class AppFontsController {

    @GetMapping("/{fontName}")
    public Resource getFontByName(@PathVariable String fontName) throws MalformedURLException {
        File fileToReturn = new File("/Users/fazcreativity/Documents/Java-Projects/ObfuscationUBHacking/App/Fonts/" + fontName);
        return new UrlResource( fileToReturn.toURI());
    }

    @GetMapping("/index")
    public Resource getIndexFile() throws MalformedURLException {
        File fileToReturn = new File("/Users/fazcreativity/Documents/Java-Projects/ObfuscationUBHacking/App/index.html");
        return new UrlResource( fileToReturn.toURI());
    }
}
