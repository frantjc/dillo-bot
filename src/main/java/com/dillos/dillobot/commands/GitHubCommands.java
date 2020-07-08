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

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;

public class GitHubCommands {

    private final Logger log = LoggerFactory.getLogger(GitHubCommands.class);

    GitHubService gitHubService;

    @Autowired
    public GitHubCommands(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @Command("/repoistory")
    public void getRepository(@Channel MessageChannel channel) {
        log.info("/repository");
    }

    @Command("/issues {state}")
    public void getIssues(
        @Channel MessageChannel channel,
        @Arg(required = false, defaultValue = "open") String state
    ) {
        log.info("/issues {}", state);

        List<IssueResponse> issues = gitHubService.getIssues(state);

        EmbedBuilder message = new EmbedBuilder()
            .setTitle(state + " issues for dillo-bot");

        for (IssueResponse issue : issues) {
            message.addField(issue.getId() + " / " + issue.getTitle(), issue.getBody(), false);
        }

        channel.sendMessage(message.build()).queue();
    }

    @Command("/createIssue {title} {body}")
    public void createIssue(
        @Channel MessageChannel channel,
        @Arg(required = true) String title,
        @Arg(required = true) String body
    ) {
        log.info("/createIssue \"{}\" \"{}\"", title, body);

        IssueResponse issue = gitHubService.createIssue(
            new IssueBuilder()
                .setTitle(title)
                .setBody(body)
                .build()
        );

        channel.sendMessage(
            new EmbedBuilder()
                .setTitle("issue created successfully", issue.getHtml_url())
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