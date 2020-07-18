package com.dillos.dillobot.dto.github;

import java.util.List;

import com.dillos.dillobot.builders.IssueBuilder;

import lombok.Data;

@Data
public class IssueRequest {

    Long id;

    Long number;

    String title;

    String body;

    String state;

    List<String> labels;

    List<String> assignees;

    public IssueRequest(IssueBuilder issue) {
        this.id = issue.getId();
        this.number = issue.getId();
        this.title = issue.getTitle();
        this.body = issue.getBody();
        this.state = issue.getState();
        this.labels = issue.getLabels();
        this.assignees = issue.getAssignees();
    }

    public IssueRequest() {}
}
