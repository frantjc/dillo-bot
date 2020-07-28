package com.dillos.dillobot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dillos.dillobot.entities.DiscordChannel;
import com.dillos.dillobot.entities.Subscription;
import com.dillos.dillobot.entities.Subscription.SubscriptionType;
import com.dillos.dillobot.repositories.DiscordChannelRepository;
import com.dillos.dillobot.repositories.SubscriptionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscordChannelService {

    Logger log = LoggerFactory.getLogger(DiscordChannelService.class);

    DiscordChannelRepository discordChannelRepository;

    SubscriptionRepository subscriptionRepository;

    @Autowired
    public DiscordChannelService(DiscordChannelRepository discordChannelRepository, SubscriptionRepository subscriptionRepository) {
        this.discordChannelRepository = discordChannelRepository;
        this.subscriptionRepository = subscriptionRepository;
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

    public List<DiscordChannel> get() {
        return discordChannelRepository.findAll();
    }

    @Transactional
    public DiscordChannel addSubscription(DiscordChannel channel, Subscription subscription) {
        Optional<DiscordChannel> maybeChannel = get(channel.getId());

        if (maybeChannel.isPresent()) {
            channel.merge(maybeChannel.get());
        } if (channel.getSubscriptions() == null) {
            channel.setSubscriptions(new ArrayList<Subscription>());
        }

        channel.getSubscriptions().add(subscription);

        return save(channel);
    }

    @Transactional
    public DiscordChannel removeSubscription(DiscordChannel channel, Subscription subscription) {
        Optional<DiscordChannel> maybeChannel = get(channel.getId());

        if (maybeChannel.isPresent()) {
            channel.merge(maybeChannel.get());
        } if (channel.getSubscriptions() == null) {
            channel.setSubscriptions(new ArrayList<Subscription>());
        }

        channel.getSubscriptions().remove(subscription);

        return save(channel);
    }

    public Subscription getSubscription(String subscription) {
        return getSubscription(SubscriptionType.valueOf(subscription.toUpperCase()));
    }

    public Subscription getSubscription(SubscriptionType subscription) {
        return subscriptionRepository.findBySubscription(subscription);
    }
}