package com.dillos.dillobot.services;

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
        return discordUserRepository.save(user);
    }
}