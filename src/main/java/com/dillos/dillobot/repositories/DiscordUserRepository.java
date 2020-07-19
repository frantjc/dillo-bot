package com.dillos.dillobot.repositories;

import java.util.Optional;

import com.dillos.dillobot.entities.DiscordUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscordUserRepository extends JpaRepository<DiscordUser, String> {

    public Optional<DiscordUser> findById(String id);

    @Query(value =
        "SELECT IF(" +
            "discord_user_id = ?1, true, false" +
        ") as linked FROM discord_git_hub_user LIMIT 1",
        nativeQuery = true
    )
    public Boolean isLinkedToGitHub(String id);
}
