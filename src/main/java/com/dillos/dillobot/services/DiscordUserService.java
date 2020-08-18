package com.dillos.dillobot.services;

import java.util.List;
import java.util.Optional;

import com.dillos.dillobot.builders.UserBuilder;
import com.dillos.dillobot.entities.DiscordUser;
import com.dillos.dillobot.entities.GitHubUser;
import com.dillos.dillobot.repositories.DiscordUserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscordUserService {

    Logger log = LoggerFactory.getLogger(DiscordUserService.class);

    DiscordUserRepository discordUserRepository;

    GitHubUserService gitHubUserService;

    @Autowired
    public DiscordUserService(DiscordUserRepository discordUserRepository, GitHubUserService gitHubUserService) {
        this.discordUserRepository = discordUserRepository;
        this.gitHubUserService = gitHubUserService;
    }

    public DiscordUser save(DiscordUser user) {
        Optional<DiscordUser> maybeUser = get(user.getId());

        if (maybeUser.isPresent()) {
            return discordUserRepository.save(
                user.merge(maybeUser.get())
            );
        }

        return discordUserRepository.save(user);
    }

    public Optional<DiscordUser> get(String id) {
        return discordUserRepository.findById(id);
    }

    public List<DiscordUser> get() {
        return discordUserRepository.findAll();
    }

    @Transactional
    public DiscordUser linkToGitHub(DiscordUser discordUser, GitHubUser gitHubUser) {
        Optional<DiscordUser> maybeDiscordUser = get(discordUser.getId());
        Optional<GitHubUser> maybeGitHubUser = gitHubUserService.get(gitHubUser.getLogin());
        Optional<GitHubUser> maybeGitHubUser2 = gitHubUserService.get(gitHubUser.getId());

        if (maybeDiscordUser.isPresent()) {
            discordUser.merge(maybeDiscordUser.get());
        } if (maybeGitHubUser.isPresent()) {
            gitHubUser.merge(maybeGitHubUser.get());
        } if (maybeGitHubUser2.isPresent()) {
            gitHubUser.merge(maybeGitHubUser2.get());
        }

        gitHubUser = gitHubUserService.save(gitHubUser);

        discordUser.setGitHubUser(gitHubUser);

        return save(discordUser);
    }

    public DiscordUser linkToGitHub(DiscordUser discordUser, String login) {
        return linkToGitHub(
            discordUser,
            new UserBuilder()
                .setLogin(login)
                .buildGitHub()
        );
    }

    public DiscordUser linkToGitHub(DiscordUser discordUser, Long gitHubId) {
        return linkToGitHub(
            discordUser,
            new UserBuilder()
                .setId(gitHubId.toString())
                .buildGitHub()
        );
    }

    public DiscordUser linkToGitHub(String discordId, GitHubUser gitHubUser) {
        return linkToGitHub(
            new UserBuilder()
                .setId(discordId)
                .build(),
            gitHubUser
        );
    }

    public DiscordUser linkToGitHub(String discordId, String login) {
        return linkToGitHub(
            discordId,
            new UserBuilder()
                .setLogin(login)
                .buildGitHub()
        );
    }

    public DiscordUser linkToGitHub(String discordId, Long gitHubId) {
        return linkToGitHub(
            discordId,
            new UserBuilder()
                .setId(gitHubId.toString())
                .buildGitHub()
        );
    }

}
