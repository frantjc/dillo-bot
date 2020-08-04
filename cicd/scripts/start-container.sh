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
    docker run -d --name dillo_bot_db -e MYSQL_ROOT_PASSWORD=$5 -e MYSQL_DATABASE=dillo_bot -e MYSQL_USER=$4 -e MYSQL_PASSWORD=$5 -p 3306:3306 mysql
fi

docker pull frantjc/dillo-bot

docker run -e GITHUB_TOKEN=$1 -e DISCORD_TOKEN=$2 -e DISCORD_CLIENT_ID=$3 -e DB_USER=$4 -e DB_PASSWORD=$5 -e DB_URI=$6 DB_DRIVER=$7 -d --name dillo_bot frantjc/dillo-bot
