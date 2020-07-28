package com.dillos.dillobot.repositories;

import com.dillos.dillobot.entities.DiscordChannel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscordChannelRepository extends JpaRepository<DiscordChannel, String> {
    
}
