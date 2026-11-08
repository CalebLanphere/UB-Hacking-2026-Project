package com.calebandwill.webserver;

import com.calebandwill.webserver.Services.TextObfuscationScrambleService;
import com.calebandwill.webserver.TextObfuscation.TextObfuscationService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class TextScrambleMapping {
    public static HashMap<Character, Character> mapOfCharactersScrambled;
}
