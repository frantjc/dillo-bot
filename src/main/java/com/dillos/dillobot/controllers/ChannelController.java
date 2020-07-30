package com.dillos.dillobot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dillos.dillobot.dto.discord.DiscordChannelResponse;
import com.dillos.dillobot.entities.DiscordChannel;
import com.dillos.dillobot.services.DiscordChannelService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/channels")
public class ChannelController {
    
    Logger log = LoggerFactory.getLogger(ChannelController.class);

    DiscordChannelService discordChannelService;

    @Autowired
    public ChannelController(DiscordChannelService discordChannelService) {
        this.discordChannelService = discordChannelService;
    }

    @GetMapping
    public ResponseEntity<List<DiscordChannelResponse>> getChannels() {
        log.info("GET /api/channels");

        return ResponseEntity.ok().body(
            discordChannelService.get().stream().map(channel -> {
                return new DiscordChannelResponse(channel);
            }).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscordChannelResponse> getChannel(String id) {
        log.info("GET /api/channels/{}", id);

        Optional<DiscordChannel> channel = discordChannelService.get(id);

        if (channel.isPresent()) {
            return ResponseEntity.ok().body(
                new DiscordChannelResponse(channel.get())
            );
        }

        return ResponseEntity.notFound().build();
    }
}
