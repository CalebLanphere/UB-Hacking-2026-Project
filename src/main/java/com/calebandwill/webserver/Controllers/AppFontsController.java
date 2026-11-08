package com.calebandwill.webserver.Controllers;

import com.calebandwill.webserver.Paths.ResourcePaths;
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
        File fileToReturn = new File(ResourcePaths.FONT_DIRECTORY + fontName);
        return new UrlResource( fileToReturn.toURI());
    }
}
