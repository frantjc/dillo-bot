package com.dillos.dillobot.commands;

import java.util.List;

import com.dillos.dillobot.annotations.Arg;
import com.dillos.dillobot.annotations.Channel;
import com.dillos.dillobot.annotations.Command;
import com.dillos.dillobot.builders.IssueBuilder;
import com.dillos.dillobot.dto.github.IssueResponse;
import com.dillos.dillobot.services.GitHubService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;

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

    @Autowired
    public GitHubCommands(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
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
            message.addField(issue.getId() + " - " + issue.getTitle(), issue.getBody(), false);
        }

        channel.sendMessage(message.build()).queue();
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

    // @Command("/updateIssue {id} {title} {body}")

    // @Command("/closeIssue {id}")

    // @Command("/claimIssue {id}")

    // @Command("/issue {id}")

    // @Command("/issueAsignees {id}")

    // @Command("/issueComments {id}")
}
