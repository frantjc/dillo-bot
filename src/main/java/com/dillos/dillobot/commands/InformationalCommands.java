package com.dillos.dillobot.commands;

import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;

@Log4j2
@Component
public class InformationalCommands {

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
        .addField("Subscriptions", "`/subscribe birthday`, `/unsubscribe birthday`", false)
        .build()
    ).queue();
  }
}
