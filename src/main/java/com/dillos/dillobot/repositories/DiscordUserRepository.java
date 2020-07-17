package com.dillos.dillobot.repositories;

import com.dillos.dillobot.entities.DiscordUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscordUserRepository extends JpaRepository<DiscordUser, String> {

}
