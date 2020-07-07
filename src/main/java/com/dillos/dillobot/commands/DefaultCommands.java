package com.dillos.dillobot.commands;

import com.dillos.dillobot.annotations.Arg;
import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;
import com.dillos.dillobot.annotations.Sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class DefaultCommands {
    
    private final Logger log = LoggerFactory.getLogger(DefaultCommands.class);

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

}