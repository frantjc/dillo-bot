package com.dillos.dillobot.dto.discord;

import com.dillos.dillobot.entities.DiscordChannel;
import com.dillos.dillobot.entities.Subscription.SubscriptionType;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class DiscordChannelResponse {
    
  String id;

  String name;

  List<SubscriptionType> subscriptions;

  public DiscordChannelResponse() {}

  public DiscordChannelResponse(DiscordChannel channel) {
    this.id = channel.getId();
    this.name = channel.getName();
    this.subscriptions = channel.getSubscriptions().stream().map(subscription -> {
      return subscription.getSubscription();
    }).collect(Collectors.toList());
  }
}
