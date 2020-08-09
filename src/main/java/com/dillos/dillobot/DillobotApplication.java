package com.dillos.dillobot;

import com.dillos.dillobot.commands.GitHubCommands;
import com.dillos.dillobot.commands.InformationalCommands;
import com.dillos.dillobot.commands.SimpleCommands;
import com.dillos.dillobot.commands.SubscriptionCommands;
import com.dillos.dillobot.services.JDAService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DillobotApplication implements CommandLineRunner {

	JDAService jdaService;

	GitHubCommands gitHubCommands;

	SimpleCommands simpleCommands;

	InformationalCommands informationalCommands;

	SubscriptionCommands subscriptionCommands;

	@Autowired
	public DillobotApplication(
		JDAService jdaService,
		GitHubCommands gitHubCommands, SimpleCommands simpleCommands, InformationalCommands informationalCommands,
		SubscriptionCommands subscriptionCommands
	) {
		this.jdaService = jdaService;
		this.gitHubCommands = gitHubCommands;
		this.simpleCommands = simpleCommands;
		this.informationalCommands = informationalCommands;
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
			informationalCommands,
			subscriptionCommands
		);

		jdaService.getJda().awaitReady();
	}

}
