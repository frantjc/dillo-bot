package com.dillos.dillobot.builders;

import java.time.LocalDate;

import com.dillos.dillobot.entities.DiscordUser;
import com.dillos.dillobot.entities.GitHubUser;
import com.dillos.dillobot.entities.UserDetails;

import lombok.Getter;

@Getter
public class UserBuilder {
    String id;

    String name;

    String discriminator;

    GitHubUser gitHubUser;

    UserDetails userDetails;

    LocalDate birthday;

    public UserBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
        return this;
    }

    public UserBuilder setGitHubUser(GitHubUser gitHubUser) {
        this.gitHubUser = gitHubUser;
        return this;
    }

    public UserBuilder setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
        return this;
    }

    public UserBuilder setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public DiscordUser build() {
        return this.buildDiscord();
    }

    public DiscordUser buildDiscord() {
        return new DiscordUser(this);
    }

    String login;

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

    DiscordUser discordUser;

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public UserBuilder setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public UserBuilder setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
        return this;
    }

    public UserBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public UserBuilder setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }

    public UserBuilder setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
        return this;
    }

    public UserBuilder setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
        return this;
    }

    public UserBuilder setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
        return this;
    }

    public UserBuilder setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
        return this;
    }

    public UserBuilder setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
        return this;
    }

    public UserBuilder setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
        return this;
    }

    public UserBuilder setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
        return this;
    }

    public UserBuilder setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
        return this;
    }

    public UserBuilder setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
        return this;
    }

    public UserBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public UserBuilder setSideAdmin(Boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
        return this;
    }

    public UserBuilder setDiscordUser(DiscordUser discordUser) {
        this.discordUser = discordUser;
        return this;
    }

    public GitHubUser buildGitHub() {
        return new GitHubUser(this);
    }
}
