package com.dillos.dillobot.dto.github;

import java.util.List;

import com.dillos.dillobot.builders.IssueBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class IssueResponse {

  String url;

  String repository_url;

  String labels_url;

  String comments_url;

  String events_url;

  String html_url;

  Long id;

  String node_id;

  Long number;

  String title;

  String state;

  Boolean locked;

  Integer comments;

  String created_at;

  String updated_at;

  String closed_at;

  String author_association;

  String active_lock_reason;

  String body;

  Object pull_request;

  List<String> labels;

  Integer milestone;

  List<GitHubUserResponse> assignees;

  GitHubUserResponse assignee;

  GitHubUserResponse user;

  public IssueResponse(IssueBuilder issue) {
    this.id = issue.getId();
    this.number = issue.getNumber();
    this.title = issue.getTitle();
    this.body = issue.getBody();
    this.state = issue.getState();
    this.labels = issue.getLabels();
  }

  public IssueResponse() {}
}
