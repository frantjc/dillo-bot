#!/bin/sh

apt-get install openssh-client

ssh key/dillo-key.pem $USER@$HOST <<- ENDSSH
    docker kill dillo_bot
ENDSSH