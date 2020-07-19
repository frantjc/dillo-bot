#!/bin/sh

if [[ $(docker ps -a | grep dillo_bot) ]]; then
    if [[ $(docker inspect dillo_bot | grep "Running" | grep true) ]]; then
        docker kill dillo_bot
        docker rm dillo_bot
    elif  [[ $(docker inspect dillo_bot | grep "Running" | grep false) ]]; then
        docker rm dillo_bot
    fi
fi

if [[ $(docker ps -a | grep dillo_bot_db) ]]; then
    if [[ $(docker inspect dillo_bot_db | grep "Running" | grep false) ]]; then
        docker start dillo_bot_db
    fi
else
    docker run -d --name dillo_bot_db -e MYSQL_ROOT_PASSWORD=$4 -e MYSQL_DATABASE=dillo-bot -e MYSQL_USER=dillo-bot -e MYSQL_PASSWORD=$4 -p 3306:3306 mysql
fi

docker pull frantjc/dillo-bot

docker run -e GITHUB_TOKEN=$1 -e DISCORD_TOKEN=$2 -e DISCORD_CLIENT_ID=$3 -d --name dillo_bot frantjc/dillo-bot
