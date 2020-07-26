package com.dillos.dillobot.services;

import java.util.ArrayList;
import java.util.Optional;

import com.dillos.dillobot.entities.DiscordChannel;
import com.dillos.dillobot.entities.Subscription;
import com.dillos.dillobot.repositories.DiscordChannelRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscordChannelService {

    Logger log = LoggerFactory.getLogger(DiscordChannelService.class);

    DiscordChannelRepository discordChannelRepository;

    @Autowired
    public DiscordChannelService(DiscordChannelRepository discordChannelRepository) {
        this.discordChannelRepository = discordChannelRepository;
    }

    public DiscordChannel save(DiscordChannel channel) {
        Optional<DiscordChannel> maybeChannel = get(channel.getId());

        if (maybeChannel.isPresent()) {
            return discordChannelRepository.save(channel.merge(maybeChannel.get()));
        }

        return discordChannelRepository.save(channel);
    }

    public Optional<DiscordChannel> get(String id) {
        return discordChannelRepository.findById(id);
    }

    public DiscordChannel addSubscription(DiscordChannel discordChannel, Subscription subscription) {
        Optional<DiscordChannel> maybeChannel = get(channel.getId());

        if (maybeChannel.isPresent()) {
            discordChannel.merge(maybeChannel.get());
        } else if (discordChannel.getSubscriptions() == null) {
            discordChannel.setSubscriptions(new ArrayList<Subscription>());
        }

        discordChannel.getSubscriptions().add(subscription);

        return save(discordChannel);
    }
}