package com.dillos.dillobot.commands;

import com.dillos.dillobot.annotations.Arg;
import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;
import com.dillos.dillobot.annotations.Sender;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

@Log4j2
@Component
public class SimpleCommands {

  @Command("/log {string}")
  public void log(
    @Sender User sender,
    @Arg(required = true) String string
  ) {
    log.info("{}: {}", sender, string);
  }

  @Command("/say {string}")
  public void say(
    @Channel MessageChannel channel,
    @Arg(required = true) String string
  ) {
    channel.sendMessage(string).queue();
  }

  @Command("/poofy")
  public void poofy(
    @Channel MessageChannel channel
  ) {
    channel.sendMessage("squirrel").queue();
  }
}
