package com.dillos.dillobot.builders;

import java.util.List;
import java.util.stream.Collectors;

import com.dillos.dillobot.entities.DiscordCategory;
import com.dillos.dillobot.entities.DiscordChannel;
import com.dillos.dillobot.entities.DiscordRole;
import com.dillos.dillobot.entities.DiscordServer;
import com.dillos.dillobot.entities.DiscordUser;

import lombok.Getter;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;

@Getter
public class ServerBuilder {
  
  String id;

  String name;

  String description;

  String region;

  String iconUrl;

  DiscordUser owner;

  DiscordChannel afkChannel;

  DiscordChannel defaultChannel;

  DiscordChannel systemChannel;

  List<DiscordChannel> channels;

  List<DiscordUser> members;

  List<DiscordCategory> categories;

  List<DiscordRole> roles;

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
      return getUserIfExistsElsewhere(member);
    }).collect(Collectors.toList());
    return this;
  }

  public ServerBuilder setRoles(List<Role> roles) {
    this.roles = roles.stream().map(role -> {
      return getRoleIfExistsElsewhere(role);
    }).collect(Collectors.toList());
    return this;
  }

  private DiscordRole getRoleIfExistsElsewhere(Role role) {
    return getRoleIfExistsElsewhere(new DiscordRole(role));
  }

  private DiscordRole getRoleIfExistsElsewhere(DiscordRole role) {
    if (this.roles != null) {
      return this.roles.stream().reduce(role, (accumulatedRole, currentRole) -> {
        if (accumulatedRole.equals(currentRole)) {
          return currentRole;
        }

        return accumulatedRole;
      });
    }

    return role;
  }

  public ServerBuilder setCategories(List<Category> categories) {
    this.categories = categories.stream().map(category -> {
      return new DiscordCategory(category);
    }).collect(Collectors.toList());
    return this;
  }

  public ServerBuilder setOwner(Member owner) {
    this.owner = getUserIfExistsElsewhere(owner);
    return this;
  }

  private DiscordUser getUserIfExistsElsewhere(Member member) {
    return getUserIfExistsElsewhere(member.getUser());
  }

  private DiscordUser getUserIfExistsElsewhere(User user) {
    return getUserIfExistsElsewhere(new DiscordUser(user));
  }

  private DiscordUser getUserIfExistsElsewhere(DiscordUser user) {
    
    if (this.owner != null && this.owner.equals(user)) {
      return this.owner;
    } else if (this.members != null) {
      int index = this.members.indexOf(user);
      if (0 <= index && index <= this.members.size() - 1) {
        return this.members.get(index);
      }
    }
  
    return user;
  }

  private DiscordChannel getChannelIfExistsElsewhere(GuildChannel channel) {
    return getChannelIfExistsElsewhere(new DiscordChannel(channel));
  }

  private DiscordChannel getChannelIfExistsElsewhere(DiscordChannel channel) {
    if (channel.equals(this.afkChannel)) {
      return afkChannel;
    } else if (channel.equals(this.defaultChannel)) {
      return defaultChannel;
    } else if (channel.equals(this.systemChannel)) {
      return systemChannel;
    } else if (this.channels != null) {
      int index = this.channels.indexOf(channel);
      if (0 <= index && index <= this.channels.size() -1) {
        return this.channels.get(index);
      }
    } else if (this.categories != null) {
      // search for channel in this.categories
    }

    return channel;
  }

  public ServerBuilder setAfkChannel(GuildChannel channel) {
    this.afkChannel = getChannelIfExistsElsewhere(channel);
    return this;
  }

  public ServerBuilder setDefaultChannel(GuildChannel channel) {
    this.afkChannel = getChannelIfExistsElsewhere(channel);
    return this;
  }

  public ServerBuilder setSystemChannel(GuildChannel channel) {
    this.afkChannel = getChannelIfExistsElsewhere(channel);
    return this;
  }

  public ServerBuilder setChannels(List<GuildChannel> channels) {
    this.channels = channels.stream().map(channel -> {
      return getChannelIfExistsElsewhere(channel);
    }).collect(Collectors.toList());
    return this;
  }

  public DiscordServer build() {
    return new DiscordServer(this);
  }
}
