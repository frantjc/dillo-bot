package com.dillos.dillobot.commands;

import com.dillos.dillobot.builders.ChannelBuilder;
import com.dillos.dillobot.entities.DiscordChannel;
import com.dillos.dillobot.entities.Subscription.SubscriptionType;
import com.dillos.dillobot.services.DiscordChannelService;
import com.dillos.dillobot.annotations.Arg;
import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.EmbedBuilder;
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
        @Arg(required = true) SubscriptionType subscription
    ) {
        log.info("/subscribe {}, for channel: {}", subscription, channel.getId());

        DiscordChannel discordChannel = new ChannelBuilder()
            .setId(channel.getId())
            .setName(channel.getName())
            .build();

        discordChannelService.addSubscription(
            discordChannel,
            discordChannelService.getSubscription(subscription)
        );

        channel.sendMessage(
            new EmbedBuilder()
                .setTitle("Subscribed")
                .setDescription("Subscribed " + discordChannel.getHashtag() + " to the " + subscription + " subscription")
                .build()
        ).queue();
    }

    @Command("/unsubscribe {subscription}")
    public void unsubscribe(
        @Channel MessageChannel channel,
        @Arg(required = true) SubscriptionType subscription
    ) {
        log.info("/unsubscribe {}, for channel: {}", subscription, channel.getId());

        DiscordChannel discordChannel = new ChannelBuilder()
            .setId(channel.getId())
            .setName(channel.getName())
            .build();

        discordChannelService.removeSubscription(
            discordChannel,
            discordChannelService.getSubscription(subscription)
        );

        channel.sendMessage(
            new EmbedBuilder()
                .setTitle("Unubscribed")
                .setDescription("Unsubscribed " + discordChannel.getHashtag() + " from the " + subscription + " subscription")
                .build()
        ).queue();
    }
}
