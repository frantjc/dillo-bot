package com.dillos.dillobot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class ReactController {

  @GetMapping("/")
  public String index() {
    log.info("GET / -> /index.html");
    return "index.html";
  }

}
