#!/bin/bash

apt-get install ssh

ssh key/dillo-key.pem $USER@$HOST <<- ENDSSH
    docker kill dillo_bot
ENDSSH