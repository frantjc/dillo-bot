package com.dillos.dillobot.commands;

import com.dillos.dillobot.builders.ChannelBuilder;
import com.dillos.dillobot.services.DiscordChannelService;
import com.dillos.dillobot.annotations.Arg;
import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.entities.MessageChannel;

@Component
public class SubscriptionCommands {

    Logger log = LoggerFactory.getLogger(SubscriptionCommands.class);

    DiscordChannelService discordChannelService;

    @Autowired
    public SubscriptionCommands(DiscordChannelService discordChannelService) {
        this.discordChannelService = discordChannelService;
    }

    @Command("/subscribe {subscription}")
    public void subscribe(
        @Channel MessageChannel channel,
        @Arg(required = true) String subscription
    ) {
        log.info("/subscribe {}, for channel: {}", subscription, channel.getId());

        discordChannelService.addSubscription(
            new ChannelBuilder()
                .setId(channel.getId())
                .setName(channel.getName())
                .build(),
            discordChannelService.getSubscription(subscription)
        );
    }

    @Command("/unsubscribe {subscription}")
    public void unsubscribe(
        @Channel MessageChannel channel,
        @Arg(required = true) String subscription
    ) {
        log.info("/unsubscribe {}, for channel: {}", subscription, channel.getId());

        discordChannelService.removeSubscription(
            new ChannelBuilder()
                .setId(channel.getId())
                .setName(channel.getName())
                .build(),
            discordChannelService.getSubscription(subscription)
        );
    }
}
