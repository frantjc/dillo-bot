package com.dillos.dillobot.dto.github;

import java.util.List;

import com.dillos.dillobot.builders.IssueBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class  IssueRequest {

    Long id;

    Long number;

    String title;

    String body;

    String state;

    List<String> labels;

    List<String> assignees;

    public IssueRequest(IssueBuilder issue) {
        this.id = issue.getId();
        this.number = issue.getNumber();
        this.title = issue.getTitle();
        this.body = issue.getBody();
        this.state = issue.getState();
        this.labels = issue.getLabels();
        this.assignees = issue.getAssignees();
    }

    public IssueRequest() {}
}
