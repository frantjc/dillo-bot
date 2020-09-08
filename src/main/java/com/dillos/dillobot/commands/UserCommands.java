package com.dillos.dillobot.commands;

import com.dillos.dillobot.annotations.Arg;
import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;
import com.dillos.dillobot.annotations.Sender;
import com.dillos.dillobot.annotations.Server;
import com.dillos.dillobot.exceptions.InvalidArgException;
import com.dillos.dillobot.services.DiscordUserService;
import com.dillos.dillobot.services.JDAService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;

@Component
public class UserCommands {

  Logger log = LoggerFactory.getLogger(UserCommands.class);

  DiscordUserService discordUserService;

  JDAService jdaService;

  @Autowired
  public UserCommands(DiscordUserService discordUserService, JDAService jdaService) {
    this.discordUserService = discordUserService;
    this.jdaService = jdaService;
  }

  @Command("/afk {at}")
  public void afk(
    @Arg(required = false, defaultValue = "") String at,
    @Sender User sender,
    @Server Guild guild,
    @Channel MessageChannel channel
  ) throws InvalidArgException {
    VoiceChannel afk = guild.getAfkChannel();

    if (afk != null) {
      String id = discordUserService.getIdFromAt(at);
      User user = jdaService.getJda().getUserById(id);

      if (user != null) {
        guild.moveVoiceMember(guild.getMember(user), afk);
      } else {
        guild.moveVoiceMember(guild.getMember(sender), afk);
      }
    } else {
      channel.sendMessage(
        new EmbedBuilder()
          .setTitle("Command Failed")
          .setDescription("Server does not have any channel set as the AFK channel")
          .build()
      ).queue();
    }
  }
}
