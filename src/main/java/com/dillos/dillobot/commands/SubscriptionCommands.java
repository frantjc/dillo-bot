package com.dillos.dillobot.commands;

import com.dillos.dillobot.builders.ChannelBuilder;
import com.dillos.dillobot.services.DiscordChannelService;
import com.dillos.dillobot.annotations.Arg;
import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;
import com.dillos.dillobot.entities.Subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.entities.MessageChannel;

@Component
public class SubscriptionCommands {
 
    DiscordChannelService discordChannelService;

    @Autowired
    public SubscriptionCommands(DiscordChannelService discordChannelService) {
        this.discordChannelService = discordChannelService;
    }

    @Command("/subscribe {subscription}")
    public void subscribe(
        @Channel MessageChannel channel,
        @Arg(required = true) Subscription subscription
    ) {
        discordChannelService.addSubscription(
            new ChannelBuilder()
                .setId(channel.getId())
                .setName(channel.getName())
                .build(),
            subscription
        );
    }
}
