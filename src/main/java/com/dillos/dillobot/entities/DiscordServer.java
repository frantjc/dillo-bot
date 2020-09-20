package com.dillos.dillobot.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

  String region;

  String iconUrl;

  @ManyToOne(
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  @JoinColumn(name = "owner_id")
  DiscordUser owner;

  @OneToOne(
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  @JoinColumn(name = "afk_channel_id")
  DiscordChannel afkChannel;

  @OneToOne(
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  @JoinColumn(name = "default_channel_id")
  DiscordChannel defaultChannel;

  @OneToOne(
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  @JoinColumn(name = "system_channel_id")
  DiscordChannel systemChannel;
  
  @ManyToMany(
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
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

  @OneToMany(
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  @JoinTable(
    name = "discord_server_category",
    joinColumns = {
      @JoinColumn(name = "discord_server_id", referencedColumnName = "id") 
    },
    inverseJoinColumns = {
      @JoinColumn(name = "discord_category_id", referencedColumnName = "id") 
    }
  )
  List<DiscordCategory> categories;

  @OneToMany(
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  @JoinTable(
    name = "discord_server_channel",
    joinColumns = {
      @JoinColumn(name = "discord_server_id", referencedColumnName = "id") 
    },
    inverseJoinColumns = {
      @JoinColumn(name = "discord_channel_id", referencedColumnName = "id") 
    }
  )
  List<DiscordChannel> channels;

  @OneToMany(
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  @JoinTable(
    name = "discord_server_role",
    joinColumns = {
      @JoinColumn(name = "discord_server_id", referencedColumnName = "id") 
    },
    inverseJoinColumns = {
      @JoinColumn(name = "discord_role_id", referencedColumnName = "id") 
    }
  )
  List<DiscordRole> roles;

  public DiscordServer(ServerBuilder builder) {
    this.id = builder.getId();
    this.name = builder.getName();
    this.description = builder.getDescription();
    this.region = builder.getRegion();
    this.iconUrl = builder.getIconUrl();
    this.owner = builder.getOwner();
    this.afkChannel = builder.getAfkChannel();
    this.defaultChannel = builder.getDefaultChannel();
    this.systemChannel = builder.getSystemChannel();
    this.members = builder.getMembers();
    this.categories = builder.getCategories();
    this.channels = builder.getChannels();
    this.roles = builder.getRoles();
  }

  public DiscordServer() {}
}
