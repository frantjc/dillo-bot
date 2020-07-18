package com.dillos.dillobot.builders;

import java.util.List;

import com.dillos.dillobot.dto.github.IssueRequest;
import com.dillos.dillobot.dto.github.IssueResponse;

import lombok.Getter;

@Getter
public class IssueBuilder {
    
    Long id;

    Long number;

    String title;

    String body;

    String state;

    List<String> labels;

    List<String> assignees;

    public IssueBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public IssueBuilder setNumber(Long number) {
        this.number = number;
        return this;
    }

    public IssueBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public IssueBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public IssueBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public IssueBuilder setLabels(List<String> labels) {
        this.labels = labels;
        return this;
    }

    public IssueBuilder setAssignees(List<String> assignees) {
        this.assignees = assignees;
        return this;
    }
    
    public IssueRequest build() {
        return this.buildRequest();
    }

    public IssueRequest buildRequest() {
        return new IssueRequest(this);
    }

    public IssueResponse buildResponse() {
        return new IssueResponse(this);
    }
}
