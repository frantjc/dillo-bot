package com.dillos.dillobot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dillos.dillobot.dto.github.IssueResponse;
import com.dillos.dillobot.entities.GitHubUser;
import com.dillos.dillobot.repositories.GitHubUserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitHubUserService {
    
    private final Logger log = LoggerFactory.getLogger(GitHubUserService.class);

    GitHubUserRepository gitHubUserRepository;

    @Autowired
    public GitHubUserService(GitHubUserRepository gitHubUserRepository) {
        this.gitHubUserRepository = gitHubUserRepository;
    }

    public GitHubUser save(GitHubUser user) {
        return gitHubUserRepository.save(user);
    }

    public List<GitHubUser> saveUsersFrom(IssueResponse response) {
        List<GitHubUser> savedUsers = new ArrayList<GitHubUser>();
        
        savedUsers.add(
            gitHubUserRepository.save(new GitHubUser(response.getUser()))
        );

        savedUsers.addAll(
            gitHubUserRepository.saveAll(
                response.getAsignees().parallelStream().map(asignee -> {
                    return new GitHubUser(asignee);
                }).collect(Collectors.toList())
            )
        );

        return savedUsers;
    }

    public List<GitHubUser> saveUsersFrom(List<IssueResponse> response) {
        List<GitHubUser> savedUsers = new ArrayList<GitHubUser>();

        response.parallelStream().forEach(issue -> {
            savedUsers.addAll(
                saveUsersFrom(issue)
            );
        });

        return savedUsers;
    }
}