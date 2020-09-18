package com.dillos.dillobot.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.dillos.dillobot.builders.ServerBuilder;

import javax.persistence.JoinColumn;

import lombok.Data;

@Entity
@Data
public class DiscordServer {

  @Id
  String id;

  String name;

  String description;
  
  @ManyToMany
  @JoinTable(
    name = "discord_server_member",
    joinColumns = {
      @JoinColumn(name = "discord_server_id", referencedColumnName = "id") 
    },
    inverseJoinColumns = {
      @JoinColumn(name = "discord_user_id", referencedColumnName = "id") 
    }
  )
  List<DiscordUser> members;

  @OneToMany
  @JoinTable(
    name = "discord_server_member",
    joinColumns = {
      @JoinColumn(name = "discord_server_id", referencedColumnName = "id") 
    },
    inverseJoinColumns = {
      @JoinColumn(name = "discord_category_id", referencedColumnName = "id") 
    }
  )
  List<DiscordCategory> categories;

  public DiscordServer(ServerBuilder builder) {
    this.id = builder.getId();
    this.name = builder.getName();
    this.description = builder.getDescription();
    this.members = builder.getMembers();
    // this.categories = builder.getCategories();
  }

  public DiscordServer() {}
}
