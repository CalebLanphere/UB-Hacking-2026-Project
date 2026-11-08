package com.teamyellow.webserver;

import org.springframework.stereotype.Controller;

@RestController
public class TestController {

    @GetMapping
    public String test() {
        return "works";
    }
}
