package com.dillos.dillobot.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import com.dillos.dillobot.builders.ChannelBuilder;

import lombok.Data;
import net.dv8tion.jda.api.entities.GuildChannel;

@Entity
@Data
public class DiscordChannel {

  @Id
  String id;

  String name;
  
  @ManyToMany
  @JoinTable(
    name = "discord_channel_subscription",
    joinColumns = {
      @JoinColumn(name = "discord_channel_id", referencedColumnName = "id") 
    },
    inverseJoinColumns = {
      @JoinColumn(name = "subscription_id", referencedColumnName = "id") 
    }
  )
  List<Subscription> subscriptions;

  public enum ChannelType {
    VOICE("VOICE"),
    TEXT("TEXT"),
    PRIVATE("PRIVATE"),
    CATEGORY("CATEGORY");

    String value;

    ChannelType(String value) {
      this.value = value.toUpperCase();
    }
  }

  @Enumerated(EnumType.STRING)
  ChannelType type;

  public String getHashtag() {
    return "<#" + this.id + ">";
  }

  public DiscordChannel merge(DiscordChannel channel) {
    if (channel.getSubscriptions() != null) {
      this.subscriptions = channel.getSubscriptions();
    } if (channel.getName() != null) {
      this.name = channel.getName();
    } if (channel.getType() != null) {
      this.type = channel.getType();
    }

    return this;
  }

  public DiscordChannel(ChannelBuilder builder) {
    this.id = builder.getId();
    this.name = builder.getName();
    this.subscriptions = builder.getSubscriptions();
    this.type = builder.getType();
  }

  public DiscordChannel(GuildChannel channel) {
    this.id = channel.getId();
    this.name = channel.getName();
    this.type = ChannelType.valueOf(channel.getType().toString());
  }

  public DiscordChannel() {}

}
