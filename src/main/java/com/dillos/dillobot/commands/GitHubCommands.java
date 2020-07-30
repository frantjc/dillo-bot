package com.dillos.dillobot.commands;

import java.util.List;
import java.util.Optional;

import com.dillos.dillobot.annotations.Arg;
import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;
import com.dillos.dillobot.annotations.Sender;
import com.dillos.dillobot.builders.IssueBuilder;
import com.dillos.dillobot.builders.UserBuilder;
import com.dillos.dillobot.dto.github.IssueResponse;
import com.dillos.dillobot.entities.DiscordUser;
import com.dillos.dillobot.entities.GitHubUser;
import com.dillos.dillobot.services.DiscordUserService;
import com.dillos.dillobot.services.GitHubService;
import com.dillos.dillobot.services.GitHubUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

@Component
public class GitHubCommands {

    Logger log = LoggerFactory.getLogger(GitHubCommands.class);

    @Value("${github.mark_uri}")
    String githubMarkUri;

    @Value("${github.logo_uri}")
    String githubLogoUri;

    @Value("${github.repository.uri}")
    String gitHubRepositoryUri;

    GitHubService gitHubService;

    GitHubUserService gitHubUserService;

    DiscordUserService discordUserService;

    @Autowired
    public GitHubCommands(GitHubService gitHubService, GitHubUserService gitHubUserService, DiscordUserService discordUserService) {
        this.gitHubService = gitHubService;
        this.gitHubUserService = gitHubUserService;
        this.discordUserService = discordUserService;
    }

    @Command("/repository")
    public void getRepository(@Channel MessageChannel channel) {
        log.info("/repository");

        EmbedBuilder message = new EmbedBuilder()
            .setThumbnail(githubMarkUri)
            .setTitle("GitHub", gitHubRepositoryUri)
            .setDescription("A link to the GitHub repository for this bot");

        channel.sendMessage(message.build()).queue();
    }

    @Command("/issues {state}")
    public void getIssues(
        @Channel MessageChannel channel,
        @Arg(required = false, defaultValue = "open") String state
    ) {
        log.info("/issues {}", state);

        List<IssueResponse> issues = gitHubService.getIssues(state);

        EmbedBuilder message = new EmbedBuilder()
            .setTitle(state + " issues for dillo-bot")
            .setThumbnail(githubLogoUri);

        for (IssueResponse issue : issues) {
            message.addField(issue.getNumber() + " - " + issue.getTitle(), issue.getBody(), false);
        }

        channel.sendMessage(message.build()).queue();
    }

    @Command("/issue {number}")
    public void request(
        @Channel MessageChannel channel,
        @Arg(required = true) Long number
    ) {
        log.info("/issue \"{}\"", number);

        IssueResponse issue = gitHubService.getIssue(number);

        channel.sendMessage(
            new EmbedBuilder()
                .setTitle(issue.getNumber() + " - " + issue.getTitle(), issue.getHtml_url())
                .setThumbnail(githubLogoUri)
                .build()
        ).queue();
    }

    @Command("/request {title} {body}")
    public void request(
        @Channel MessageChannel channel,
        @Arg(required = true) String title,
        @Arg(required = true) String body
    ) {
        log.info("/request \"{}\" \"{}\"", title, body);

        IssueResponse issue = gitHubService.createIssue(
            new IssueBuilder()
                .setTitle(title)
                .setBody(body)
                .build()
        );

        channel.sendMessage(
            new EmbedBuilder()
                .setTitle("issue created successfully", issue.getHtml_url())
                .setThumbnail(githubLogoUri)
                .build()
        ).queue();
    }

    @Command("/updateIssue {number} {title} {body}")
    public void updateIssue(
        @Channel MessageChannel channel,
        @Arg(required = true) Long number,
        @Arg(required = true) String title,
        @Arg(required = true) String body
    ) {
        log.info("/updateIssue \"{}\" \"{}\" \"{}\"", number, title, body);

        IssueResponse issue = gitHubService.updateIssue(
            new IssueBuilder()
                .setId(number)
                .setTitle(title)
                .setBody(body)
                .build()
        );

        channel.sendMessage(
            new EmbedBuilder()
                .setTitle("issue updated successfully", issue.getHtml_url())
                .setThumbnail(githubLogoUri)
                .build()
        ).queue();
    }

    @Command("/closeIssue {number}")
    public void closeIssue(
        @Channel MessageChannel channel,
        @Arg(required = true) Long number
    ) {
        log.info("/closeIssue \"{}\"", number);

        IssueResponse issue = gitHubService.closeIssue(number);

        channel.sendMessage(
            new EmbedBuilder()
                .setTitle("issue closed", issue.getHtml_url())
                .setThumbnail(githubLogoUri)
                .build()
        ).queue();
    }

    @Command("/linkGitHub {login}")
    public void linkGitHub(
        @Channel MessageChannel channel,
        @Arg(required = true) String login,
        @Sender User discordUser
    ) {
        log.info("/linkGitHub \"{}\"", login);

        Optional<GitHubUser> maybeGitHubUser = gitHubUserService.get(login);

        EmbedBuilder message = new EmbedBuilder();

        if (maybeGitHubUser.isPresent()) {
            GitHubUser gitHubUser = maybeGitHubUser.get();

            if (gitHubUser.isLinked()) {
                message
                    .setTitle("failed to link accounts", gitHubUser.getHtmlUrl())
                    .setDescription("GitHub account \"" + login + "\" already linked to another user")
                    .setThumbnail(githubLogoUri);
            } else {
                discordUserService.save(
                    new UserBuilder()
                        .setId(discordUser.getId())
                        .setName(discordUser.getName())
                        .setDiscriminator(discordUser.getDiscriminator())
                        .setGitHubUser(gitHubUser)
                        .build()
                );

                message.setTitle("accounts linked successfully", gitHubUser.getHtmlUrl()).setThumbnail(githubLogoUri);
            }
        } else {
            message
                .setTitle("failed to link accounts")
                .setDescription("GitHub account with login \"" + login + "\" not found");
        }

        channel.sendMessage(message.build()).queue();
    }

    @Command("/claimRequest {number}")
    public void claimRequest(
        @Channel MessageChannel channel,
        @Arg(required = true) Long number,
        @Sender User discordUser
    ) {
        log.info("/claimRequest {}", number);

        Optional<DiscordUser> maybeUser = discordUserService.get(discordUser.getId());

        if (maybeUser.isPresent()) {
            DiscordUser user = maybeUser.get();

            EmbedBuilder message = new EmbedBuilder();

            if (user.isLinked()) {
                IssueResponse response = gitHubService.claimIssue(number, user.getGitHubUser());

                message
                    .setTitle("issue claimed by \"" + user.getGitHubUser().getLogin() + "\"", response.getHtml_url())
                    .setThumbnail(githubLogoUri);
            } else {
                message
                    .setTitle("failed to claim request")
                    .setDescription("Please link your account to a GitHub account by using /linkGitHub {login}");
            }
            
            channel.sendMessage(
                message.build()
            ).queue();
        }
    }

    @Command("/claimIssue {number}")
    public void claimIssue(
        @Channel MessageChannel channel,
        @Arg(required = true) Long number,
        @Sender User discordUser
    ) {
        claimRequest(channel, number, discordUser);
    }
}
