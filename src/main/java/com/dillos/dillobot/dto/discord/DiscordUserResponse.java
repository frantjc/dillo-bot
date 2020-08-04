package com.dillos.dillobot.dto.discord;

import java.time.LocalDate;

import com.dillos.dillobot.entities.DiscordUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class DiscordUserResponse {
    
    String id;

    String name;

    String discriminator;

    LocalDate birthday;

    public DiscordUserResponse() {}

    public DiscordUserResponse(DiscordUser user) {
        this.id = user.getId();
        this.name = user.getName();
        this.discriminator = user.getDiscriminator();
        this.birthday = user.getBirthday();
    }

}
