package com.dillos.dillobot.repositories;

import java.util.Optional;

import com.dillos.dillobot.entities.GitHubUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GitHubUserRepository extends JpaRepository<GitHubUser, Long> {
    
    public Optional<GitHubUser> findByLogin(String login);

    @Query(value =
        "SELECT EXISTS(" +
            "SELECT * FROM DISCORD_GIT_HUB_USER " + 
            "WHERE GIT_HUB_USER_ID = ?1" +
        ")",
        nativeQuery = true
    )
    public Boolean isLinked(Long id);
}
