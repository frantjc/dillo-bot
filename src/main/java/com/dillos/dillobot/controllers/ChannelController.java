package com.dillos.dillobot.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dillos.dillobot.dto.SubscriptionResponse;
import com.dillos.dillobot.dto.discord.DiscordChannelResponse;
import com.dillos.dillobot.entities.DiscordChannel;
import com.dillos.dillobot.entities.Subscription.SubscriptionType;
import com.dillos.dillobot.services.DiscordChannelService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("api/channels")
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

    List<DiscordChannelResponse> channels = discordChannelService.get().stream().map(channel -> {
      return new DiscordChannelResponse(channel);
    }).collect(Collectors.toList());

    if (channels.size() > 0) {
      return ResponseEntity.ok().body(channels);
    }

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<DiscordChannelResponse> getChannel(@PathVariable String id) {
    log.info("GET /api/channels/{}", id);

    Optional<DiscordChannel> channel = discordChannelService.get(id);

    if (channel.isPresent()) {
      return ResponseEntity.ok().body(new DiscordChannelResponse(channel.get()));
    }

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}/subscriptions")
  public ResponseEntity<List<SubscriptionResponse>> getSubscriptions(@PathVariable String id) {
    log.info("GET /api/channels/{}/subscriptions", id);

    List<SubscriptionResponse> subscriptions = discordChannelService.get(id).orElseGet(DiscordChannel::new)
      .getSubscriptions().stream().map(subscription -> {
        return new SubscriptionResponse(subscription);
      }).collect(Collectors.toList());

    if (subscriptions.size() > 0) {
      return ResponseEntity.ok().body(subscriptions);
    }

    return ResponseEntity.noContent().build();
  }

  @RequestMapping(value = "/{id}/subscriptions", method = { 
    RequestMethod.POST,
    RequestMethod.PUT,
    RequestMethod.PATCH
  })
  public ResponseEntity<DiscordChannelResponse> addSubscription(@PathVariable String id, @RequestBody SubscriptionType subscription)
          throws URISyntaxException {
            log.info("POST|PUT|PATCH /api/channels/{}/susbcriptions {}", id, subscription);

            return ResponseEntity.created(new URI("/api/channels/" + id)).body(
              new DiscordChannelResponse(
                discordChannelService.addSubscription(id, subscription)
              )
            );
  }
}
