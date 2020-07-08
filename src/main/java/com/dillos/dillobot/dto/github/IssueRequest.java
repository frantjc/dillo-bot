package com.dillos.dillobot.dto.github;

import com.dillos.dillobot.builders.IssueBuilder;

import lombok.Data;

@Data
public class IssueRequest {

    String title;

    String body;

    public IssueRequest(IssueBuilder issue) {
        this.title = issue.getTitle();
        this.body = issue.getBody();
    }

    public IssueRequest() {}
}