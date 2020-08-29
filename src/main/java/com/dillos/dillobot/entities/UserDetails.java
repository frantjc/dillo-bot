package com.dillos.dillobot.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.dillos.dillobot.dto.UserDetailsRequest;

import lombok.Data;

@Entity
@Data
public class UserDetails {
    
    @Id
    @GeneratedValue
    Long id;

    LocalDate birthday;

    @OneToOne
    @JoinColumn(name = "discord_user_id")
    DiscordUser discordUser;

    public UserDetails(UserDetailsRequest userDetails) {
        this.birthday = userDetails.getBirthday();
    }

    public UserDetails() {}

}