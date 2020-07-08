package com.dillos.dillobot.builders;

import com.dillos.dillobot.dto.github.IssueRequest;
import com.dillos.dillobot.dto.github.IssueResponse;

import lombok.Getter;

@Getter
public class IssueBuilder {
    
    String title;

    String body;

    public IssueBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public IssueBuilder setBody(String body) {
        this.body = body;
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