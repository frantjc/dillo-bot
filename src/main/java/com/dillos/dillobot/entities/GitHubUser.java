package com.dillos.dillobot.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.dillos.dillobot.builders.UserBuilder;
import com.dillos.dillobot.dto.github.GitHubUserResponse;

import lombok.Data;

@Entity
@Data
public class GitHubUser {

  String login;

  @Id
  Long id;

  String nodeId;

  String avatarUrl;

  String gravatarId;

  String url;

  String htmlUrl;

  String followersUrl;

  String followingUrl;

  String gistsUrl;

  String starredUrl;

  String subscriptionsUrl;

  String organizationsUrl;

  String reposUrl;

  String eventsUrl;

  String receivedEventsUrl;

  String type;

  Boolean siteAdmin;
  
  @OneToOne(mappedBy = "gitHubUser")
  DiscordUser discordUser;

  public GitHubUser merge(GitHubUser user) {
    if (user.getDiscordUser() != null) {
      this.discordUser = user.getDiscordUser();
    }

    return this;
  }

  public Boolean isSiteAdmin() {
    return this.siteAdmin;
  }

  public Boolean isLinked() {
    return discordUser != null;
  }

  public GitHubUser(UserBuilder builder) {
    this.login = builder.getLogin();
    this.id = Long.parseLong(builder.getId());
    this.nodeId = builder.getNodeId();
    this.gravatarId = builder.getGravatarId();
    this.url = builder.getUrl();
    this.htmlUrl = builder.getHtmlUrl();
    this.followersUrl = builder.getFollowersUrl();
    this.followingUrl = builder.getFollowingUrl();
    this.gistsUrl = builder.getGistsUrl();
    this.starredUrl = builder.getStarredUrl();
    this.subscriptionsUrl = builder.getSubscriptionsUrl();
    this.organizationsUrl = builder.getOrganizationsUrl();
    this.reposUrl = builder.getReposUrl();
    this.eventsUrl = builder.getEventsUrl();
    this.receivedEventsUrl = builder.getReceivedEventsUrl();
    this.type = builder.getType();
    this.siteAdmin = builder.getSiteAdmin();
    this.discordUser = builder.getDiscordUser();
  }

  public GitHubUser(GitHubUserResponse user) {
    this.login = user.getLogin();
    this.id = user.getId();
    this.nodeId = user.getNode_id();
    this.gravatarId = user.getGravatar_id();
    this.url = user.getUrl();
    this.htmlUrl = user.getHtml_url();
    this.followersUrl = user.getFollowers_url();
    this.followingUrl = user.getFollowing_url();
    this.gistsUrl = user.getGists_url();
    this.starredUrl = user.getStarred_url();
    this.subscriptionsUrl = user.getSubscriptions_url();
    this.organizationsUrl = user.getOrganizations_url();
    this.reposUrl = user.getRepos_url();
    this.eventsUrl = user.getEvents_url();
    this.receivedEventsUrl = user.getReceived_events_url();
    this.type = user.getType();
    this.siteAdmin = user.getSite_admin();
  }

  public GitHubUser() {}
}
