package com.dillos.dillobot.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dillos.dillobot.builders.IssueBuilder;
import com.dillos.dillobot.dto.github.IssueRequest;
import com.dillos.dillobot.dto.github.IssueResponse;
import com.dillos.dillobot.entities.GitHubUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GitHubService {

    Logger log = LoggerFactory.getLogger(GitHubService.class);

    @Value("${github.repository.api}")
    String api;

    @Value("${github.repository.token}")
    String token;

    RestTemplate rest;

    GitHubUserService gitHubUserService;

    @Autowired
    public GitHubService(RestTemplate rest, GitHubUserService gitHubUserService) {
        this.rest = rest;
        this.gitHubUserService = gitHubUserService;
    }

    public List<IssueResponse> getIssues(String state) {
        URI uri = UriComponentsBuilder.fromUriString(api).path("/issues").queryParam("state", state).build().encode().toUri();

        List<IssueResponse> response = Optional.ofNullable(rest.getForObject(uri, IssueResponse[].class))
                .map(Arrays::asList).orElseGet(ArrayList::new);

        gitHubUserService.saveUsersFrom(response);

        return response.stream().filter(issue -> {
            return issue.getPull_request() == null;
        }).collect(Collectors.toList());
    }

    public IssueResponse getIssue(Long number) {
        URI uri = UriComponentsBuilder.fromUriString(api)
            .path("/issues").path("/{issueNumber}")
            .build(number);

        IssueResponse response = Optional.ofNullable(
            rest.getForObject(
                uri,
                IssueResponse.class
            )
        ).orElseGet(IssueResponse::new);

        gitHubUserService.saveUsersFrom(response);

        return response;
    }

    public IssueResponse getIssue(IssueRequest issue) {
        return getIssue(issue.getNumber());
    }

    public IssueResponse createIssue(IssueRequest issue) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        URI uri = UriComponentsBuilder.fromUriString(api)
            .path("/issues")
            .build().encode().toUri();

        return Optional.ofNullable(
            rest.postForObject(
                uri,
                new HttpEntity<IssueRequest>(issue, headers),
                IssueResponse.class
            )
        ).orElseGet(IssueResponse::new);
    }

    public IssueResponse updateIssue(IssueRequest issue) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        URI uri = UriComponentsBuilder.fromUriString(api)
            .path("/issues").path("/{issueNumber}")
            .build(issue.getNumber());
    
        return Optional.ofNullable(
            rest.patchForObject(
                uri,
                new HttpEntity<IssueRequest>(issue, headers),
                IssueResponse.class
            )
        ).orElseGet(IssueResponse::new);
    }

    public IssueResponse claimIssue(Long number, GitHubUser user) {
        List<String> assignees = new ArrayList<String>();
        assignees.add(user.getLogin());

        return updateIssue(
            new IssueBuilder()
                .setNumber(number)
                .setAssignees(assignees)
                .build()
        );
    }

    public IssueResponse claimIssue(IssueRequest issue, GitHubUser user) {
        List<String> assignees = new ArrayList<String>();
        assignees.add(user.getLogin());

        return updateIssue(
            new IssueBuilder()
                .setNumber(issue.getNumber())
                .setAssignees(assignees)
                .build()
        );
    }

    public IssueResponse claimIssue(IssueRequest issue) {
        return updateIssue(
            new IssueBuilder()
                .setNumber(issue.getNumber())
                .setAssignees(issue.getAssignees())
                .build()
        );
    }

    public IssueResponse closeIssue(Long number) {
        return updateIssue(
            new IssueBuilder()
                .setNumber(number)
                .setState("closed")
                .build()
        );
    }

    public IssueResponse closeIssue(IssueRequest issue) {
        return closeIssue(issue.getNumber());
    }
}
