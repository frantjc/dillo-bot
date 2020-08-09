package com.dillos.dillobot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dillos.dillobot.dto.discord.DiscordUserResponse;
import com.dillos.dillobot.dto.github.GitHubUserResponse;
import com.dillos.dillobot.entities.DiscordUser;
import com.dillos.dillobot.entities.GitHubUser;
import com.dillos.dillobot.services.DiscordUserService;
import com.dillos.dillobot.services.GitHubUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {
    
    Logger log = LoggerFactory.getLogger(UserController.class);

    GitHubUserService gitHubUserService;

    DiscordUserService discordUserService;

    @Autowired
    public UserController(GitHubUserService gitHubUserService, DiscordUserService discordUserService) {
        this.gitHubUserService = gitHubUserService;
        this.discordUserService = discordUserService;
    }

    @GetMapping("/github")
    public ResponseEntity<List<GitHubUserResponse>> getGitHubUsers() {
        log.info("GET /api/users/github");
    
        return ResponseEntity.ok().body(
            gitHubUserService.get().stream().map(user -> {
                return new GitHubUserResponse(user);
            }).collect(Collectors.toList())
        );
    }

    @GetMapping("/github/{id}")
    public ResponseEntity<GitHubUserResponse> getGitHubUser(@PathVariable Long id) {
        log.info("GET /api/users/github/{}", id);
    
        Optional<GitHubUser> user = gitHubUserService.get(id);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(
                new GitHubUserResponse(user.get())
            );
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/discord")
    public ResponseEntity<List<DiscordUserResponse>> getDiscordUsers() {
        log.info("GET /api/users/discord");

        return ResponseEntity.ok().body(
            discordUserService.get().stream().map(user -> {
                return new DiscordUserResponse(user);
            }).collect(Collectors.toList())
        );
    }

    @GetMapping("/discord/{id}")
    public ResponseEntity<DiscordUserResponse> getDiscordUser(@PathVariable String id) {
        log.info("GET /api/users/discord/{}", id);

        Optional<DiscordUser> user = discordUserService.get(id);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(
                new DiscordUserResponse(user.get())
            );
        }

        return ResponseEntity.notFound().build();
    }

}
