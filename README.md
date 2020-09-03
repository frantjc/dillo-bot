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

Lastly, on GitHub, you should make a [Pull Request](https://github.com/frantjc/dillo-bot/pulls), asking for your code to be merged into develop.

More Reading                                         |
-----------------------------------------------------|
[Concourse](documentaion/concourse/Concourse.md)     |
[Docker](documentation/docker/Docker.md)             |
[FlyWay](documentation/flyway/FlyWay.md)             |
[JDA](documentation/jda/JDA.md)                      |
[Maven](documentation/maven/Maven.md)                |
[SpringBoot](documentation/springboot/SpringBoot.md) |
[VSCode](documentation/vscode/VSCode.md)             |

## Running locally

When running the app locally, you will be connecting to DilloBot-d. To successfully connect to a DilloBot (and for some of DilloBot's commands to successfully run), the file [`src/main/resources/application.yml`](src/main/resources/application.yml) must have some secret tokens and ids in it. Ask the owner of this repository about that.

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

# dillo-bot-ui

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.<br />
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.<br />
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.<br />
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.<br />
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.<br />
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

If you aren’t satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you’re on your own.

You don’t have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn’t feel obligated to use this feature. However we understand that this tool wouldn’t be useful if you couldn’t customize it when you are ready for it.