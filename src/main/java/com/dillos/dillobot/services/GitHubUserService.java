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
    
    public Optional<GitHubUser> get(Long id) {
        return gitHubUserRepository.findById(id);
    }

    public Optional<GitHubUser> get(String login) {
        return gitHubUserRepository.findByLogin(login);
    }

    public GitHubUser save(GitHubUser user) {
        Optional<GitHubUser> maybeUser = get(user.getId());

        if (maybeUser.isPresent()) {
            return gitHubUserRepository.save(
                user.merge(maybeUser.get())
            );
        }

        return gitHubUserRepository.save(user);
    }

    public List<GitHubUser> saveAll(List<GitHubUser> users) {
        return gitHubUserRepository.saveAll(users.stream().map(user -> {
            Optional<GitHubUser> maybeUser = get(user.getId());

            if (maybeUser.isPresent()) {
                return user.merge(maybeUser.get());
            }

            return user;
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
