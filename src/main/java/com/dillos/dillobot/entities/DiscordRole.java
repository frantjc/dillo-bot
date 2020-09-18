package com.dillos.dillobot.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class DiscordRole {

  @Id
  String id;

  String name;

  String color;

}
