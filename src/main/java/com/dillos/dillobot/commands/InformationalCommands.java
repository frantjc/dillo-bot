package com.dillos.dillobot.commands;

import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;

@Component
public class InformationalCommands {

    Logger log = LoggerFactory.getLogger(InformationalCommands.class);

    @Value("${discord.bot.redirect_uri}")
    String redirectUri;
 
    @Command("/help")
    public void help(@Channel MessageChannel channel) {
        log.info("/help");

        channel.sendMessage(
            new EmbedBuilder()
                .setTitle("DilloBot", redirectUri)
                .setDescription("by Discord server Dillos the Third. Available commands:")
                .addField("Informational", "`/help`", false)
                .addField("GitHub", "`/request`, `/issues`, `/repository`, `/issue`, `/updateIssue`, `/closeIssue`, `/claimIssue`, `/linkGitHub`", false)
                .addField("Subscriptions", "`/subscribe birthday`", false)
                .build()
        ).queue();
    }
}
