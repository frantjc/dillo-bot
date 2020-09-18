package com.dillos.dillobot.builders;

import java.util.List;
import java.util.stream.Collectors;

import com.dillos.dillobot.entities.DiscordCategory;
import com.dillos.dillobot.entities.DiscordServer;
import com.dillos.dillobot.entities.DiscordUser;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Member;

@Getter
public class ServerBuilder {
  
  String id;

  String name;

  String description;

  List<DiscordUser> members;

  List<DiscordCategory> categories;

  public ServerBuilder setId(String id) {
    this.id = id;
    return this;
  }

  public ServerBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public ServerBuilder setDescription(String description) {
    this.description = description;
    return this;
  }
  
  public ServerBuilder setMembers(List<Member> members) {
    this.members = members.stream().map(member -> {
      return new DiscordUser(member.getUser());
    }).collect(Collectors.toList());
    return this;
  }

  public ServerBuilder setCategories(List<Category> categories) {
    this.categories = categories.stream().map(category -> {
      return new DiscordCategory(category);
    }).collect(Collectors.toList());
    return this;
  }

  public DiscordServer build() {
    return new DiscordServer(this);
  }
}
