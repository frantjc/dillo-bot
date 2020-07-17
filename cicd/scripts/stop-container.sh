#!/bin/sh

ssh $USER@$HOST <<- ENDSSH
    docker kill dillo_bot
ENDSSH