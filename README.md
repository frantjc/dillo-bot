# dillo-bot

A bot for Discord server Dillos the Third.

## Setup

### Install

#### [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

#### [Git](https://git-scm.com/)

#### Editor suggestion: [VSCode](https://code.visualstudio.com/)

> _Hint: there are a bunch of useful Java, SpringBoot, etc. extensions for VSCode that you can add inside of the editor itself!_

### Verify

On the command line, ensure a correct version of Java is on your PATH:
```
$ java --version
openjdk 11 2018-09-25
OpenJDK Runtime Environment 18.9 (build 11+28)
OpenJDK 64-Bit Server VM 18.9 (build 11+28, mixed mode)
```

Do the same for Git:
```
$ git --version
git version 2.26.2.windows.1
```

## Developing

### Cloning the repository

On the command line, in the directory you want the project in:
```
$ git clone https://github.com/frantjc/dillo-bot.git
```

### Creating your own branch to work in

If you want to work on GitHub integration, for example, you would:
```
$ git checkout develop
$ git pull
$ git checkout -b my_github_branch
```

This ensures that you have the most recent code to start with on your branch.


Then, once you are satisfied with your work:
```
$ git add .
$ git commit -m "I integrated dillo-bot with GitHub"
$ git push
```

Lastly, on GitHub, you should make a Pull Request, asking for your code to be merged into develop.

## Running locally

When running the app locally, you will be connecting to DilloBot-d. To successfully connect to a DilloBot (and for some of DilloBot's commands to successfully run), the file `src/main/resources/application.yml` must have some secret tokens and ids in it. Ask the owner of this repository about that.

As a SpringBoot/Maven project, dillo-bot can be ran in a number of ways.  The easiest and most preferred method, in the root of the project, follows:
```
$ mvnw spring-boot:run
```

Or, to run it inside of Docker from source:
```
$ cp cicd\docker\Dockerfile .
$ docker build .
```

> _Note: Docker is available on Windows 10 Pro, Mac, and Linux._

## Creating a command

I kind of created my own annotation-driven development pattern for adding commands. It is built upon JDA and modeled after SpringBoot's own annotation system for HTTP requests.

Create a class with some commands:
```java
// src/main/java/com/dillos/dillobot/commands/MyCommands.java

...

@Component
public class MyCommands {

    @Command("/myCommand {someArg}")
    public void myCommand(
        @Arg(defaultValue = "mine") someArg,
        @Channel MessageChannel channel
    ) {
        channel.sendMessage(someArg).queue();
    }

}
```

Add your commands to the bot:
```java
// src/main/java/com/dillos/dillobot/DillobotApplication.java

...

    MyCommands myCommands; // add your @Component class with some @Command functions

    // @Autowired your commands
    @Autowired
	public DillobotApplication(
		JDAService jdaService,
        GitHubCommands gitHubCommands, SimpleCommands simpleCommands,
        InformationalCommands informationalCommands, MyCommands myCommands
	) {
		this.jdaService = jdaService;
		this.gitHubCommands = gitHubCommands;
		this.simpleCommands = simpleCommands;
        this.informationalCommands = informationalCommands;
        this.myCommands = myCommands; // instantiate your commands
    }
    
...

	@Override
	public void run(String... args) throws Exception {
		jdaService.start();

		jdaService.addCommands(
			gitHubCommands,
			simpleCommands,
            informationalCommands,
            myCommands // add your commands to the jda!
		);

		jdaService.getJda().awaitReady();
    }

...
```

Done! If you are interested in the code behind this, it exists largely in `src/main/java/com/dillos/dillobot/services/JDAService.java` and `src/main/java/com/dillos/dillobot/annotations`.  I think it's pretty cool.
