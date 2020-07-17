package com.dillos.dillobot.repositories;

import com.dillos.dillobot.entities.GitHubUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitHubUserRepository extends JpaRepository<GitHubUser, String> {
    
}
