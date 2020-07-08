package com.dillos.dillobot.commands;

import com.dillos.dillobot.annotations.Arg;
import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;
import com.dillos.dillobot.annotations.Sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

@Component
public class SimpleCommands {
    
    private final Logger log = LoggerFactory.getLogger(SimpleCommands.class);

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