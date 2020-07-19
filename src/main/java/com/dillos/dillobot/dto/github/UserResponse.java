package com.dillos.dillobot.dto.github;

import com.dillos.dillobot.entities.GitHubUser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class UserResponse {
    
    String login;

    Long id;

    String node_id;

    String avatar_url;

    String gravatar_id;

    String url;

    String html_url;

    String followers_url;

    String following_url;

    String gists_url;

    String starred_url;

    String subscriptions_url;

    String organizations_url;

    String repos_url;

    String events_url;

    String received_events_url;

    String type;

    Boolean site_admin;

    public Boolean isSite_admin() {
        return this.site_admin;
    }

    public UserResponse() {}

    public UserResponse(GitHubUser user) {
        this.login = user.getLogin();
        this.id = user.getId();
        this.node_id = user.getNodeId();
        this.avatar_url = user.getAvatarUrl();
        this.gravatar_id = user.getGravatarId();
        this.url = user.getUrl();
        this.html_url = user.getHtmlUrl();
        this.followers_url = user.getFollowersUrl();
        this.following_url = user.getFollowingUrl();
        this.gists_url = user.getGistsUrl();
        this.starred_url = user.getStarredUrl();
        this.subscriptions_url = user.getSubscriptionsUrl();
        this.organizations_url = user.getOrganizationsUrl();
        this.repos_url = user.getReposUrl();
        this.events_url = user.getEventsUrl();
        this.received_events_url = user.getReceivedEventsUrl();
        this.type = user.getType();
        this.site_admin = user.getSiteAdmin();
    }
}
