package com.dillos.dillobot;

import com.dillos.dillobot.commands.GitHubCommands;
import com.dillos.dillobot.commands.InformationCommands;
import com.dillos.dillobot.commands.SimpleCommands;
import com.dillos.dillobot.commands.SubscriptionCommands;
import com.dillos.dillobot.services.JDAService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DillobotApplication implements CommandLineRunner {

	JDAService jdaService;

	GitHubCommands gitHubCommands;

	SimpleCommands simpleCommands;

	InformationCommands informationCommands;

	SubscriptionCommands subscriptionCommands;

	@Autowired
	public DillobotApplication(
		JDAService jdaService,
		GitHubCommands gitHubCommands, SimpleCommands simpleCommands, InformationCommands informationCommands,
		SubscriptionCommands subscriptionCommands
	) {
		this.jdaService = jdaService;
		this.gitHubCommands = gitHubCommands;
		this.simpleCommands = simpleCommands;
		this.informationCommands = informationCommands;
		this.subscriptionCommands = subscriptionCommands;
	}

	public static void main(String[] args) {
		SpringApplication.run(DillobotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jdaService.start();

		jdaService.addCommands(
			gitHubCommands,
			simpleCommands,
			informationCommands,
			subscriptionCommands
		);

		jdaService.getJda().awaitReady();
	}
}
