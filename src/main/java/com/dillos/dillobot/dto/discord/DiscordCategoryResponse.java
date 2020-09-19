package com.dillos.dillobot.dto.discord;

import java.util.List;
import java.util.stream.Collectors;

import com.dillos.dillobot.entities.DiscordCategory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
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
