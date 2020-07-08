package com.dillos.dillobot;

import com.dillos.dillobot.commands.GitHubCommands;
import com.dillos.dillobot.commands.SimpleCommands;
import com.dillos.dillobot.services.GitHubService;
import com.dillos.dillobot.services.JDAService;
import com.dillos.dillobot.services.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DillobotApplication implements CommandLineRunner {

	@Value("${discord.bot.token}")
	String token;

	JDAService jdaService;

	RestService restService;

	GitHubService gitHubService;

	@Autowired
	public DillobotApplication(JDAService jdaService, RestService restService, GitHubService gitHubService) {
		this.jdaService = jdaService;
		this.restService = restService;
		this.gitHubService = gitHubService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DillobotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jdaService.start(token);

		jdaService.addCommands(
			new GitHubCommands(gitHubService),
			new SimpleCommands()
		);

		jdaService.getJda().awaitReady();
	}

}
