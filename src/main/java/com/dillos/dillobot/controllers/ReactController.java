package com.dillos.dillobot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactController {
    
    Logger log = LoggerFactory.getLogger(ReactController.class);

    @GetMapping("/")
    public String index() {
        log.info("GET / -> /index.html");
        return "index.html";
    }

}
