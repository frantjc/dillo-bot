package com.dillos.dillobot.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.dillos.dillobot.dto.github.UserResponse;
import com.dillos.dillobot.services.GitHubUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    
    Logger log = LoggerFactory.getLogger(UserController.class);

    GitHubUserService gitHubUserService;

    @Autowired
    public UserController(GitHubUserService gitHubUserService) {
        this.gitHubUserService = gitHubUserService;
    }

    @GetMapping("/github")
    public List<UserResponse> getGitHubUsers() {
        log.info("GET /api/users/github");
    
        return gitHubUserService.get().stream().map(user -> {
            return new UserResponse(user);
        }).collect(Collectors.toList());
    }
}