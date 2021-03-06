package com.dillos.dillobot.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.dillos.dillobot.builders.UserBuilder;

import lombok.Data;
import net.dv8tion.jda.api.entities.User;

@Entity
@Data
public class DiscordUser {

  @Id
  String id;

  String name;

  String discriminator;

  @OneToOne
  @JoinTable(
    name = "discord_git_hub_user", 
    joinColumns = {
      @JoinColumn(name = "discord_user_id", referencedColumnName = "id") 
    },
    inverseJoinColumns = {
      @JoinColumn(name = "git_hub_user_id", referencedColumnName = "id") 
    }
  )
  GitHubUser gitHubUser;

  @OneToOne(mappedBy = "discordUser")
  UserDetails userDetails;

  public DiscordUser merge(DiscordUser user) {
    if (user.getGitHubUser() != null) {
      this.gitHubUser = user.getGitHubUser();
    } if (user.getUserDetails() != null) {
      this.userDetails = user.getUserDetails();
    } if (user.getName() != null) {
      this.name = user.getName();
    } if (user.getDiscriminator() != null) {
      this.discriminator = user.getDiscriminator();
    }

    return this;
  }

  public LocalDate getBirthday() {
    if (this.userDetails == null) {
      return null;
    }

    return this.userDetails.getBirthday();
  }

  public void setBirthday(LocalDate birthday) {
    if (this.userDetails == null) {
      this.userDetails = new UserDetails();
    }

    this.userDetails.setBirthday(birthday);
  }

  public Boolean isLinked() {
    return gitHubUser != null;
  }

  public String getAt() {
    return "<@" + this.id + ">";
  }

  public DiscordUser(UserBuilder builder) {
    this.id = builder.getId();
    this.name = builder.getName();
    this.discriminator = builder.getDiscriminator();
    this.gitHubUser = builder.getGitHubUser();
    if (builder.getUserDetails() != null) {
      this.userDetails = builder.getUserDetails();
    } if (builder.getBirthday() != null) {
      this.setBirthday(builder.getBirthday());
    }
  }

  public DiscordUser(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.discriminator = user.getDiscriminator();
  }

  public DiscordUser() {}

}
