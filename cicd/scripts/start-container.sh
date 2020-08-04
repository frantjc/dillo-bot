#!/bin/sh

if [[ "$(docker inspect -f '{{.State.Running}}' dillo_bot)" == "true" ]]; then
    docker kill dillo_bot
fi

if  [[ "$(docker inspect -f '{{.State.Running}}' dillo_bot)" == "false" ]]; then
    docker rm dillo_bot
fi

if [[ "$(docker inspect -f '{{.State.Running}}' dillo_bot_db)" == "false" ]]; then
    docker start dillo_bot_db
elif [[ "$(docker inspect -f '{{.State.Running}}' dillo_bot_db)" != "true" ]]; then
    docker run -d --name dillo_bot_db -e MYSQL_ROOT_PASSWORD=$5 -e MYSQL_DATABASE=dillo_bot -e MYSQL_USER=$4 -e MYSQL_PASSWORD=$5 -p 3306:3306 mysql
fi

docker pull frantjc/dillo-bot

docker run -e GITHUB_TOKEN=$1 -e DISCORD_TOKEN=$2 -e DISCORD_CLIENT_ID=$3 -e DB_USER=$4 -e DB_PASSWORD=$5 -e DB_URI=$6 -e DB_DRIVER=$7 -d --name dillo_bot frantjc/dillo-bot
