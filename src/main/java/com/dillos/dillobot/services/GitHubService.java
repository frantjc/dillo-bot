package com.dillos.dillobot.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.dillos.dillobot.dto.github.IssueRequest;
import com.dillos.dillobot.dto.github.IssueResponse;

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
        URI uri = UriComponentsBuilder.fromUriString(api)
            .path("/issues")
            .queryParam("state", state)
            .build().encode().toUri();

        List<IssueResponse> response = Optional.ofNullable(
            rest.getForObject(
                uri,
                IssueResponse[].class
            )
        ).map(Arrays::asList).orElseGet(ArrayList::new);

        // gitHubUserService.saveUsersFrom(response);

        return response;
    }

    public IssueResponse createIssue(IssueRequest issue) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        URI uri = UriComponentsBuilder.fromUriString(api)
            .path("/issues")
            .build().encode().toUri();

        IssueResponse response = Optional.ofNullable(
            rest.postForObject(
                uri,
                new HttpEntity<IssueRequest>(issue, headers),
                IssueResponse.class
            )
        ).orElseGet(IssueResponse::new);

        // gitHubUserService.saveUsersFrom(response);

        return response;
    }
}
