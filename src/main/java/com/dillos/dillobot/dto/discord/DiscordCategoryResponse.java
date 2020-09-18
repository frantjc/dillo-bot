package com.dillos.dillobot.dto.discord;

import java.util.List;
import java.util.stream.Collectors;

import com.dillos.dillobot.entities.DiscordCategory;

public class DiscordCategoryResponse {

  String id;

  String name;

  List<DiscordChannelResponse> channels;

  public DiscordCategoryResponse() {}

  public DiscordCategoryResponse(DiscordCategory category) {
    this.id = category.getId();
    this.name = category.getName();
    this.channels = category.getChannels().stream().map(channel -> {
      return new DiscordChannelResponse(channel);
    }).collect(Collectors.toList());
  }
}
