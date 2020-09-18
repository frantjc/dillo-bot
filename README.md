# dillo-bot

A bot for Discord server Dillos the Third.

## Setup

### Install

#### [Java 14](https://www.oracle.com/java/technologies/javase-jdk14-downloads.html)

#### [Git](https://git-scm.com/)

#### [Nodejs 12.x](https://nodejs.org/en/download/)

> _Note: Nodejs is only required for developing DilloBot's ui_

#### [Yarn](https://yarnpkg.com/)

> _Note: Yarn is an optional package manager replacement for Nodejs's npm.  It is also only relevant to DilloBot's ui_

#### Editor suggestion: [VSCode](https://code.visualstudio.com/)

> _Hint: there are a bunch of useful Java, SpringBoot, etc. extensions for VSCode that you can add inside of the editor itself!_

### Verify

On the command line, ensure a correct version of Java is on your PATH:
```
$ java --version
openjdk 14.0.1 2020-04-14
OpenJDK Runtime Environment (build 14.0.1+7-Ubuntu-1ubuntu1)
OpenJDK 64-Bit Server VM (build 14.0.1+7-Ubuntu-1ubuntu1, mixed mode, sharing)
```

Do the same for Git:
```
$ git --version
git version 2.25.1
```

Lastly (and optionally), for nodejs and one of its package managers:
```
$ node --version
v12.18.3
$ npm --version
6.14.6
$ yarn --version
1.22.5
```

## Developing

### Cloning the repository

On the command line, in the directory you want the project folder to be in:
```
$ git clone https://github.com/frantjc/dillo-bot.git
```

### Creating your own branch to work in

If you want to work on GitHub integration, for example, you would do the following in the project root directory:
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

Lastly, on GitHub, you should make a [Pull Request](https://github.com/frantjc/dillo-bot/pulls), asking for your code to be merged into develop.

More Reading                                        |
----------------------------------------------------|
[Concourse](documentaion/concourse/Concourse.md)    |
[Docker](documentation/docker/Docker.md)            |
[FlyWay](documentation/flyway/FlyWay.md)            |
[JDA](documentation/jda/JDA.md)                     |
[Maven](documentation/maven/Maven.md)               |
[SpringBoot](documentation/springboot/SpringBoot.md)|
[VSCode](documentation/vscode/VSCode.md)            |
[React](documentation/react/React.md)               |

## Running locally

When running the app locally, you will be connecting to DilloBot-d. To successfully connect to a DilloBot (and for some of DilloBot's commands to successfully run), the file [`src/main/resources/application.yml`](src/main/resources/application.yml) must have some secret tokens and ids in it. Ask the owner of this repository about that.

As a SpringBoot/Maven project, dillo-bot can be ran in a number of ways.  To just run the api:
```
$ mvnw spring-boot:run
```

To build the ui into the api and run it:
```
$ npm run build:start // or build:run
```

To just run the ui:
```
$ npm start
```

Or, to run it inside of Docker from source:
```
$ cp cicd/docker/dillo-bot/src/Dockerfile .
$ docker run .
```

> _Note: Docker is available on Windows 10 Pro, Mac, and Linux._

## Creating a command

I kind of created my own annotation-driven development pattern for adding commands. It is built upon JDA and modeled after SpringBoot's own annotation system for HTTP requests.

Create a class with some commands:
```java
package com.dillos.dillobot.commands;

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
package com.dillos.dillobot;

...

  MyCommands myCommands; // add your @Component class with some @Command functions

  // @Autowired your commands
  @Autowired
	public DillobotApplication(
		JDAService jdaService,
    SimpleCommands simpleCommands,
    MyCommands myCommands
	) {
		this.jdaService = jdaService;
		this.simpleCommands = simpleCommands;
    this.myCommands = myCommands; // instantiate your commands
  }
    
...

	@Override
	public void run(String... args) throws Exception {
		jdaService.start();

		jdaService.addCommands(
			simpleCommands,
      myCommands // add your commands to the jda!
		);

		jdaService.getJda().awaitReady();
  }

...
```

Done! If you are interested in the code behind this, it exists largely in [`src/main/java/com/dillos/dillobot/services/JDAService.java`](src/main/java/com/dillos/dillobot/services/JDAService.java) and [`src/main/java/com/dillos/dillobot/annotations`](src/main/java/com/dillos/dillobot/annotations).  I think it's pretty cool.
