package com.dillos.dillobot.builders;

import java.util.List;

import com.dillos.dillobot.entities.DiscordChannel;
import com.dillos.dillobot.entities.Subscription;
import com.dillos.dillobot.entities.DiscordChannel.ChannelType;

import lombok.Getter;

@Getter
public class ChannelBuilder {

  String id;

  String name;
  
  List<Subscription> subscriptions;

  ChannelType type;

  public ChannelBuilder setId(String id) {
    this.id = id;
    return this;
  }

  public ChannelBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public ChannelBuilder setSubscriptions(List<Subscription> subscriptions) {
    this.subscriptions = subscriptions;
    return this;
  }

  public DiscordChannel build() {
    return new DiscordChannel(this);
  }

  public ChannelBuilder setType(String type) {
    this.type = ChannelType.valueOf(type);
    return this;
  }
}
