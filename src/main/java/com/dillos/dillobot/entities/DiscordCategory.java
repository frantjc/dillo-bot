package com.dillos.dillobot.entities;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import lombok.Data;
import net.dv8tion.jda.api.entities.Category;

@Entity
@Data
public class DiscordCategory {

@Id
  String id;

  String name;

  @OneToMany
  @JoinTable(
    name = "discord_category_channel",
    joinColumns = {
      @JoinColumn(name = "discord_category_id", referencedColumnName = "id")
    },
    inverseJoinColumns = {
      @JoinColumn(name = "discord_channel_id", referencedColumnName = "id")
    }
  )
  List<DiscordChannel> channels;

  public DiscordCategory(Category category) {
    this.id = category.getId();
    this.name = category.getName();
    this.channels = category.getChannels().stream().map(channel -> {
      return new DiscordChannel(channel);
    }).collect(Collectors.toList());
  }
  
  public DiscordCategory() {}
}
