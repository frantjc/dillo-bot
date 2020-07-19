package com.dillos.dillobot.entities;

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
            @JoinColumn(referencedColumnName = "id") 
        }
    )
    GitHubUser gitHubUser;

    public DiscordUser(UserBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.discriminator = builder.getDiscriminator();
        this.gitHubUser = builder.getGitHubUser();
    }

    public DiscordUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.discriminator = user.getDiscriminator();
    }

    public DiscordUser() {}
}
