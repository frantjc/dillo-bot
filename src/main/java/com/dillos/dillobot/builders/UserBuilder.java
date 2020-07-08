package com.dillos.dillobot.builders;

import com.dillos.dillobot.entities.User;

import lombok.Getter;

@Getter
public class UserBuilder {
    String id;

    String name;

    String discriminator;

    public UserBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
        return this;
    }

    public User build() {
        return new User(this);
    }
}