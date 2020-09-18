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
  
  List<DiscordUserResponse> members;

  List<DiscordCategoryResponse> categories;

  public DiscordServerResponse() {}

  public DiscordServerResponse(DiscordServer server) {
    this.id = server.getId();
    this.name = server.getName();
    this.description = server.getDescription();
    this.members = server.getMembers().stream().map(member -> {
      return new DiscordUserResponse(member);
    }).collect(Collectors.toList());
    this.categories = server.getCategories().stream().map(category -> {
      return new DiscordCategoryResponse(category);
    }).collect(Collectors.toList());
  }
}
