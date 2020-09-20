package com.dillos.dillobot.dto.discord;

import java.util.List;
import java.util.stream.Collectors;

import com.dillos.dillobot.entities.DiscordServer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class DiscordServerResponse {
  
  String id;

  String name;

  String description;
  
  String region;

  String iconUrl;

  DiscordUserResponse owner;

  DiscordChannelResponse afkChannel;

  DiscordChannelResponse defaultChannel;

  DiscordChannelResponse systemChannel;

  List<DiscordUserResponse> members;

  List<DiscordCategoryResponse> categories;

  List<DiscordChannelResponse> channels;

  public DiscordServerResponse() {}

  public DiscordServerResponse(DiscordServer server) {
    this.id = server.getId();
    this.name = server.getName();
    this.description = server.getDescription();
    this.region = server.getRegion();
    this.iconUrl = server.getIconUrl();
    if (server.getOwner() != null) {
      this.owner = new DiscordUserResponse(server.getOwner());
    } if (server.getAfkChannel() != null) {
      this.afkChannel = new DiscordChannelResponse(server.getAfkChannel());
    } if (server.getDefaultChannel() != null) {
      this.defaultChannel = new DiscordChannelResponse(server.getDefaultChannel());
    } if (server.getSystemChannel() != null) {
      this.systemChannel = new DiscordChannelResponse(server.getSystemChannel());
    }
    this.members = server.getMembers().stream().map(member -> {
      return new DiscordUserResponse(member);
    }).collect(Collectors.toList());
    this.categories = server.getCategories().stream().map(category -> {
      return new DiscordCategoryResponse(category);
    }).collect(Collectors.toList());
    this.channels = server.getChannels().stream().map(channel -> {
      return new DiscordChannelResponse(channel);
    }).collect(Collectors.toList());
  }
}
