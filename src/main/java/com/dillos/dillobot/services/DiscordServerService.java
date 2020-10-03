package com.dillos.dillobot.services;

import java.util.List;
import java.util.Optional;

import com.dillos.dillobot.entities.DiscordServer;
import com.dillos.dillobot.repositories.DiscordServerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscordServerService {

  DiscordServerRepository discordServerRepository;

  @Autowired
  public DiscordServerService(DiscordServerRepository discordServerRepository) {
    this.discordServerRepository = discordServerRepository;
  }

  public DiscordServer save(DiscordServer server) {
    return discordServerRepository.save(server);
  }

  public List<DiscordServer> get() {
    return discordServerRepository.findAll();
  }

  public Optional<DiscordServer> get(String id) {
    return discordServerRepository.findById(id);
  }
}
