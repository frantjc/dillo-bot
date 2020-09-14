package com.dillos.dillobot.commands;

import com.dillos.dillobot.annotations.Arg;
import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;
import com.dillos.dillobot.annotations.Sender;
import com.dillos.dillobot.annotations.Server;
import com.dillos.dillobot.services.DiscordUserService;
import com.dillos.dillobot.services.JDAService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
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
  ) {
    log.info("/afk {}, from user: {}, in guild: {}", at, sender, guild);
  
    VoiceChannel afk = guild.getAfkChannel();

    String id = discordUserService.getIdFromAt(at);

    if (id != null) {
      Member member = guild.getMemberById(id);

      log.info("afk channel: {}, member: {}", afk, member);

      return;
    }

    guild.moveVoiceMember(guild.getMember(sender), afk).queue();
  }
}
