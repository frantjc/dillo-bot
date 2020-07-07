package com.dillos.dillobot.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.LoginException;

import com.dillos.dillobot.annotations.Arg;
import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;
import com.dillos.dillobot.annotations.Event;
import com.dillos.dillobot.annotations.Server;
import com.dillos.dillobot.exceptions.InvalidCommandPrefixException;
import com.dillos.dillobot.annotations.Message;
import com.dillos.dillobot.annotations.Sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Service("jdaService")
public class JDAService {

    private final Logger log = LoggerFactory.getLogger(JDAService.class);

    @Value("${discord.bot.prefix}")
    String prefix;

    JDA jda;

    @Bean("jda")
    public JDA jda() throws LoginException {
        return this.jda;
    }

    public void start(String token) throws LoginException {
        this.jda = JDABuilder.createDefault(token).build();
    }

    public void addListeners(Object... listeners) {
        this.jda.addEventListener(listeners);
    }

    public void addCommands(Object... commands) throws InvalidCommandPrefixException {
        for (Object command : commands) {
            Method[] methods = command.getClass().getMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Command.class)) {
                    String usage = method.getAnnotation(Command.class).value();

                    if (!usage.startsWith(prefix)) {
                        throw new InvalidCommandPrefixException("all @Commands must start with \"" + prefix + "\" from discord.bot.prefix property");
                    }

                    List<String> expectedArgs = Arrays.stream(usage.replaceAll("(\\{|\\})", "").split(" ")).skip(1).collect(Collectors.toList());
                    List<Parameter> expectedParams = Arrays.stream(method.getParameters()).collect(Collectors.toList());

                    this.addListeners(new ListenerAdapter() {
                        @Override
                        public void onMessageReceived(MessageReceivedEvent event) {
                            List<String> args = Arrays.stream(
                                event.getMessage().getContentRaw().split(" ")
                            ).collect(Collectors.toList());

                            if (
                                Arrays.stream(
                                    usage.split(" ")
                                ).collect(Collectors.toList()).get(0).equals(
                                    args.get(0)
                                )
                            ) {
                                if (event.getAuthor().isBot()) {
                                    log.warn("@Commands can't be sent by bots");
                                    return;
                                }

                                args.remove(0);
                                Object[] params = expectedParams.stream().map(param -> {
                                    if (param.isAnnotationPresent(Event.class)) {
                                        return event;
                                    } else if (param.isAnnotationPresent(Sender.class)) {
                                        return event.getAuthor();
                                    } else if (param.isAnnotationPresent(Message.class)) {
                                        return event.getMessage();
                                    } else if (param.isAnnotationPresent(Channel.class)) {
                                        return event.getChannel();
                                    } else if (param.isAnnotationPresent(Server.class)) {
                                        return event.getGuild();
                                    } else if (
                                        param.isAnnotationPresent(Arg.class)
                                        && expectedArgs.contains(param.getName())
                                        && args.size() > expectedArgs.indexOf(param.getName())
                                    ) {
                                        return args.get(expectedArgs.indexOf(param.getName()));
                                    } else if (
                                        param.isAnnotationPresent(Arg.class)
                                        && param.getAnnotation(Arg.class).required()
                                    ) {
                                        event.getChannel().sendMessage(
                                            new EmbedBuilder()
                                                .setFooter(usage)
                                                .build()
                                        ).queue();
                                        log.warn("Missing required @Arg on @Command");
                                    }
                                    return null;
                                }).toArray(Object[]::new);

                                try {
                                    method.invoke(command, params);
                                } catch (IllegalAccessException | IllegalArgumentException
                                        | InvocationTargetException e) {
                                            log.warn("{}", e);
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    public void addCommand(Class<?>... commands) throws InvalidCommandPrefixException {
        try {
            for (Class<?> command : commands) {
                this.addCommands(command.getDeclaredConstructor().newInstance());
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            log.warn("{}", e);
        }
    }
}