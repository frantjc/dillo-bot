# [Docker](https://www.docker.com/)

The currently-in-use image of [DilloBot](https://hub.docker.com/repository/docker/frantjc/dillo-bot) is hosted on [DockerHub](https://hub.docker.com/) as well as being deployed inside of a Docker container, although you don't need to know anything about Docker to work on the project.  All of the Docker code is hidden inside of [`cicd/`](../../cicd).

## Running using Docker:

```
$ docker run -d -e GITHUB_TOKEN=$1 -e DISCORD_TOKEN=$2 -e DISCORD_CLIENT_ID=$3 -e DB_USER=$4 -e DB_PASSWORD=$5 -e DB_URI=$6 frantjc/dillo-bot
```
