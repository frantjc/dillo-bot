package com.dillos.dillobot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    
    Logger log = LoggerFactory.getLogger(GitHubUserService.class);

    GitHubUserRepository gitHubUserRepository;

    @Autowired
    public GitHubUserService(GitHubUserRepository gitHubUserRepository) {
        this.gitHubUserRepository = gitHubUserRepository;
    }

    public List<GitHubUser> get() {
        return gitHubUserRepository.findAll();
    }
    
    public GitHubUser get(Long id) {
        return gitHubUserRepository.getOne(id);
    }

    public Boolean exists(Long id) {
        return gitHubUserRepository.existsById(id);
    }

    public Optional<GitHubUser> get(String login) {
        return gitHubUserRepository.findByLogin(login);
    }

    public GitHubUser save(GitHubUser user) {
        if (!exists(user.getId())) {
            return gitHubUserRepository.save(user);
        }

        return get(user.getId());
    }

    public List<GitHubUser> saveAll(List<GitHubUser> users) {
        return gitHubUserRepository.saveAll(users.stream().filter(user -> {
            return !exists(user.getId());
        }).collect(Collectors.toList()));
    }

    public List<GitHubUser> saveUsersFrom(IssueResponse response) {
        List<GitHubUser> savedUsers = new ArrayList<GitHubUser>();
    
        savedUsers.add(
            save(new GitHubUser(response.getUser()))
        );

        savedUsers.addAll(
            saveAll(
                response.getAssignees().stream().map(assignee -> {
                    return new GitHubUser(assignee);
                }).collect(Collectors.toList())
            )
        );

        return savedUsers;
    }

    public List<GitHubUser> saveUsersFrom(List<IssueResponse> response) {
        List<GitHubUser> savedUsers = new ArrayList<GitHubUser>();

        response.stream().forEach(issue -> {
            savedUsers.addAll(
                saveUsersFrom(issue)
            );
        });

        return savedUsers;
    }
}
