package com.dillos.dillobot.services;

import java.util.Optional;

import com.dillos.dillobot.entities.DiscordUser;
import com.dillos.dillobot.repositories.DiscordUserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscordUserService {

    Logger log = LoggerFactory.getLogger(DiscordUserService.class);

    DiscordUserRepository discordUserRepository;

    @Autowired
    public DiscordUserService(DiscordUserRepository discordUserRepository) {
        this.discordUserRepository = discordUserRepository;
    }

    public DiscordUser save(DiscordUser user) {
        if (!exists(user.getId())) {
            return discordUserRepository.save(user);
        }

        return get(user.getId()).get();
    }

    public Optional<DiscordUser> get(String id) {
        return discordUserRepository.findById(id);
    }

    public Boolean isLinkedToGitHub(String id) {
        return discordUserRepository.isLinkedToGitHub(id);
    }

    public Boolean exists(String id) {
        return discordUserRepository.existsById(id);
    }
}
