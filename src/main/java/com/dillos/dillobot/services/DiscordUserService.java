package com.dillos.dillobot.services;

import java.util.List;
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
}
