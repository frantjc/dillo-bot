package com.dillos.dillobot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dillos.dillobot.dto.discord.DiscordChannelResponse;
import com.dillos.dillobot.dto.discord.DiscordServerResponse;
import com.dillos.dillobot.dto.discord.DiscordUserResponse;
import com.dillos.dillobot.entities.DiscordServer;
import com.dillos.dillobot.services.DiscordServerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/servers")
public class ServerController {

  Logger log = LoggerFactory.getLogger(ServerController.class);

  DiscordServerService discordServerService;

  @Autowired
  public ServerController(DiscordServerService discordServerService) {
    this.discordServerService = discordServerService;
  }

  @GetMapping
  public ResponseEntity<List<DiscordServerResponse>> getServers() {
    log.info("GET /api/servers");

    List<DiscordServerResponse> servers = discordServerService.get().stream().map(server -> {
      return new DiscordServerResponse(server);
    }).collect(Collectors.toList());

    if (servers.size() > 0) {
      return ResponseEntity.ok().body(servers);
    }

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<DiscordServerResponse> getServer(@PathVariable String id) {
    log.info("GET /api/servers/{}", id);

    Optional<DiscordServer> server = discordServerService.get(id);

    if (server.isPresent()) {
      return ResponseEntity.ok().body(new DiscordServerResponse(server.get()));
    }

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}/members")
  public ResponseEntity<List<DiscordUserResponse>> getServerMembers(@PathVariable String id) {
    log.info("GET /api/servers/{}/members", id);

    Optional<DiscordServer> server = discordServerService.get(id);

    if (server.isPresent()) {
      return ResponseEntity.ok().body(server.get().getMembers().stream().map(member -> {
        return new DiscordUserResponse(member);
      }).collect(Collectors.toList()));
    }

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}/owner")
  public ResponseEntity<DiscordUserResponse> getServerOwner(@PathVariable String id) {
    log.info("GET /api/servers/{}/owner", id);

    Optional<DiscordServer> server = discordServerService.get(id);

    if (server.isPresent()) {
      return ResponseEntity.ok().body(new DiscordUserResponse(server.get().getOwner()));
    }

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}/channels")
  public ResponseEntity<List<DiscordChannelResponse>> getServerChannels(@PathVariable String id) {
    log.info("GET /api/servers/{}/members", id);

    Optional<DiscordServer> server = discordServerService.get(id);

    if (server.isPresent()) {
      return ResponseEntity.ok().body(server.get().getChannels().stream().map(channel -> {
        return new DiscordChannelResponse(channel);
      }).collect(Collectors.toList()));
    }

    return ResponseEntity.noContent().build();
  }
}
