package com.dillos.dillobot.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.dillos.dillobot.builders.UserBuilder;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import net.dv8tion.jda.api.entities.User;

@Entity
@Data
public class DiscordUser {

    @Id
    String id;

    String name;

    String discriminator;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    @JsonManagedReference("gitHubDiscord")
    GitHubUser gitHubUser;

    public DiscordUser(UserBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.discriminator = builder.getDiscriminator();
    }

    public DiscordUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.discriminator = user.getDiscriminator();
    }

    public DiscordUser() {}
}
