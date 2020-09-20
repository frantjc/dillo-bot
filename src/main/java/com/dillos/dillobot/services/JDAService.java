package com.dillos.dillobot.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.LoginException;

import com.dillos.dillobot.annotations.Arg;
import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;
import com.dillos.dillobot.annotations.Event;
import com.dillos.dillobot.annotations.Server;
import com.dillos.dillobot.builders.ServerBuilder;
import com.dillos.dillobot.exceptions.InvalidCommandException;
import com.dillos.dillobot.annotations.Message;
import com.dillos.dillobot.annotations.Sender;

import org.apache.tools.ant.types.Commandline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Service("jdaService")
public class JDAService {

  Logger log = LoggerFactory.getLogger(JDAService.class);

  @Value("${discord.bot.prefix}")
  String prefix;

  @Value("${discord.bot.token}")
  String token;

  JDA jda;

  List<String> addedCommands;

  @Bean("jda")
  public JDA getJda() {
    return this.jda;
  }

  DiscordServerService discordServerService;

  @Autowired
  public JDAService(DiscordServerService discordServerService) throws LoginException {
    this.discordServerService = discordServerService;
  }

  public void start() throws LoginException {
    this.jda = JDABuilder.createDefault(token).build();
    this.addedCommands = new ArrayList<String>();
  }

  public void addListener(Object listener) {
    this.addListeners(listener);
  }

  public void addListeners(Object... listeners) {
    this.jda.addEventListener(listeners);
  }

  public void saveDiscordEntitiesFrom(MessageReceivedEvent event) {
    Guild server = event.getGuild();

    discordServerService.save(
      new ServerBuilder()
        .setId(server.getId())
        .setName(server.getName())
        .setDescription(server.getDescription())
        .setMembers(server.getMembers())
        // see ServerBuilder.getChannelIfExistsElsewhere()
        // .setCategories(server.getCategories())
        .setOwner(server.getOwner())
        // incorrectly sets #general as the server AFK channel presently
        // .setAfkChannel(server.getAfkChannel())
        // .setDefaultChannel(server.getDefaultChannel())
        // .setSystemChannel(server.getSystemChannel())
        .setChannels(server.getChannels(false))
        // .setRoles(server.getRoles())
        .build()
    );
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public Object castArgToParam(String arg, Parameter param) {
    Class<?> parameterizedParamClass = param.getType();

    if (parameterizedParamClass.equals(String.class)) {
      return arg;
    }
  
    if (parameterizedParamClass.isEnum()) {
      Class paramClass = param.getType();
      return Enum.valueOf(paramClass, arg.toUpperCase());
    }

    try {
      return parameterizedParamClass.getDeclaredConstructor(arg.getClass()).newInstance(arg);
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
            | NoSuchMethodException | SecurityException e) {
      log.warn(
        "Could not create {} using a Constructor for arg of Type {} in Class {} with error: {}",
        parameterizedParamClass.getName(),
        arg.getClass().getName(),
        parameterizedParamClass.getName(),
        e
      );

      return parameterizedParamClass.cast(arg);
    }
  }

  public void addCommand(Object command) throws InvalidCommandException {
    for (Method method : command.getClass().getMethods()) {
      if (method.isAnnotationPresent(Command.class)) {
        String[] usages = method.getAnnotation(Command.class).value();

        for (String usage : usages) {
          if (!usage.startsWith(prefix)) {
              throw new InvalidCommandException("all @Commands must start with \"" + prefix + "\" from discord.bot.prefix property");
          }

          List<String> expectedArgs = Arrays.stream(usage.replaceAll("(\\{|\\})", "").split(" ")).collect(Collectors.toList());
          String name = expectedArgs.remove(0);

          if (addedCommands.contains(name.toLowerCase())) {
              throw new InvalidCommandException("multiple @Commands cannot have the same name: " + name);
          }

          addedCommands.add(name.toLowerCase());

          List<Parameter> expectedParams = Arrays.stream(method.getParameters()).collect(Collectors.toList());

          this.addListeners(new ListenerAdapter() {
            boolean shouldInvoke = true;

            @Override
            public void onMessageReceived(MessageReceivedEvent event) {
              saveDiscordEntitiesFrom(event);

              if (
                name.toLowerCase().equals(
                  Arrays.stream(
                    event.getMessage().getContentRaw().split(" ")
                  ).collect(Collectors.toList()).get(0).toLowerCase()
                )
              ) {
                if (event.getAuthor().isBot()) {
                  log.warn("@Commands can't be sent by bots");
                  return;
                }

                List<String> args = Arrays.stream(
                  Commandline.translateCommandline(
                    event.getMessage().getContentRaw().replace('“', '"')
                  )
                ).collect(Collectors.toList());

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
                    return castArgToParam(
                      args.get(
                        expectedArgs.indexOf(param.getName())
                      ),
                      param
                    );
                  } else if (
                    param.isAnnotationPresent(Arg.class)
                    && !param.getAnnotation(Arg.class).defaultValue().isEmpty()
                  ) {
                    return castArgToParam(
                      param.getAnnotation(Arg.class).defaultValue(),
                      param
                    );
                  } else if (
                    param.isAnnotationPresent(Arg.class)
                    && param.getAnnotation(Arg.class).required()
                    && shouldInvoke
                  ) {
                    event.getChannel().sendMessage(
                      new EmbedBuilder()
                        .setTitle("Invalid command provided")
                        .addField("Usage:", "`" + usage + "`", true)
                        .build()
                    ).queue();
                    log.warn("Missing required @Arg on @Command");
                    shouldInvoke = false;
                  }
                  return null;
                }).toArray(Object[]::new);

                try {
                  if (shouldInvoke) {
                    method.invoke(command, params);
                  }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                  log.warn("{}", e);
              
                  event.getChannel().sendMessage(
                    new EmbedBuilder()
                      .setTitle("Command failed")
                      .addField("Usage:", "`" + usage + "`", true)
                      .build()
                  ).queue();
                }
                shouldInvoke = true;
              }
            }
          });
        }
      }
    }
  }

  public void addCommands(Object... commands) throws InvalidCommandException {
    for (Object command : commands) {
      this.addCommand(command);
    }
  }
}
