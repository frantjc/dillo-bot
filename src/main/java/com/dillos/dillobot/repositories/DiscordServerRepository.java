package com.dillos.dillobot.repositories;

import com.dillos.dillobot.entities.DiscordServer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscordServerRepository extends JpaRepository<DiscordServer, String> {

}
