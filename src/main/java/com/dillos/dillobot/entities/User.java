package com.dillos.dillobot.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.dillos.dillobot.builders.UserBuilder;

import lombok.Data;

@Entity
@Data
public class User {

    @Id
    String id;

    String name;

    String discriminator;

    public User(UserBuilder user) {
        this.id = user.getId();
        this.name = user.getName();
        this.discriminator = user.getDiscriminator();
    }

    public User() {}
}