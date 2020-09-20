package com.dillos.dillobot.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import net.dv8tion.jda.api.entities.Role;

@Entity
@Data
public class DiscordRole {

  @Id
  String id;

  String name;

  String color;

  public DiscordRole(Role role) {
    this.id = role.getId();
    this.name = role.getName();
    this.color = role.getColor().toString();
  }

  public DiscordRole() {}

}
