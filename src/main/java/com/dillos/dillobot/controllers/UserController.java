package com.dillos.dillobot.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dillos.dillobot.builders.UserBuilder;
import com.dillos.dillobot.dto.UserDetailsResponse;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

        List<GitHubUserResponse> users = gitHubUserService.get().stream().map(user -> {
            return new GitHubUserResponse(user);
        }).collect(Collectors.toList());

        if (users.size() > 0) {
            return ResponseEntity.ok().body(users);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/github/{id}")
    public ResponseEntity<GitHubUserResponse> getGitHubUser(@PathVariable Long id) {
        log.info("GET /api/users/github/{}", id);

        Optional<GitHubUser> user = gitHubUserService.get(id);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(new GitHubUserResponse(user.get()));
        }

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/github/{github_id}/link", method = {
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.PATCH
    })
    public ResponseEntity<GitHubUserResponse> linkToDiscord(
        @PathVariable Long github_id,
        @RequestBody String discord_id
    ) throws URISyntaxException {
        log.info("POST|PUT|PATCH /api/users/github/{}/link {}", github_id, discord_id);
    
        return ResponseEntity.created(new URI("/api/users/github/" + github_id)).body(
            new GitHubUserResponse(
                discordUserService.linkToGitHub(discord_id, github_id).getGitHubUser()
            )
        );
    }

    @GetMapping("/discord")
    public ResponseEntity<List<DiscordUserResponse>> getDiscordUsers() {
        log.info("GET /api/users/discord");

        List<DiscordUserResponse> users = discordUserService.get().stream().map(user -> {
            return new DiscordUserResponse(user);
        }).collect(Collectors.toList());

        if (users.size() > 0) {
            return ResponseEntity.ok().body(users);
        }

        return ResponseEntity.noContent().build();
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

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/discord/{id}/details")
    public ResponseEntity<UserDetailsResponse> getDiscordUserDetails(@PathVariable String id) {
        log.info("GET /api/users/discord/{}/details", id);

        UserDetailsResponse details = new UserDetailsResponse(discordUserService.get(id).orElseGet(DiscordUser::new).getUserDetails());

        return ResponseEntity.ok().body(details);
    }

    @GetMapping("/discord/{id}/birthday")
    public ResponseEntity<LocalDate> getDiscordUserBirthday(@PathVariable String id) {
        log.info("GET /api/users/discord/{}/details", id);

        LocalDate birthday = discordUserService.get(id).orElseGet(DiscordUser::new).getBirthday();

        return ResponseEntity.ok().body(birthday);
    }

    @RequestMapping(value = "/discord/{id}/birthday", method = {
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.PATCH
    })
    public ResponseEntity<DiscordUserResponse> addBirthday(
        @PathVariable String id,
        @RequestBody LocalDate birthday
    ) throws URISyntaxException {
        log.info("POST|PUT|PATCH /api/users/discord/{}/birthday {}", id, birthday);

        Optional<DiscordUser> maybeUser = discordUserService.get(id);

        DiscordUser discordUser = new UserBuilder()
            .setId(id)
            .build();

        if (maybeUser.isPresent()) {
            discordUser.merge(maybeUser.get());
        }

        discordUser.setBirthday(birthday);

        return ResponseEntity.created(new URI("/api/users/discord/" + id)).body(
            new DiscordUserResponse(
                discordUserService.save(
                    discordUser
                )
            )
        );
    }

    @RequestMapping(value = "/discord/{discord_id}/link", method = {
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.PATCH
    })
    public ResponseEntity<DiscordUserResponse> linkToDiscord(
        @PathVariable String discord_id,
        @RequestBody Long github_id
    ) throws URISyntaxException {
        log.info("POST|PUT|PATCH /api/users/discord/{}/link {}", discord_id, github_id);
    
        return ResponseEntity.created(new URI("/api/users/discord/" + discord_id)).body(
            new DiscordUserResponse(
                discordUserService.linkToGitHub(discord_id, github_id)
            )
        );
    }

}
