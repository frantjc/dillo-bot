#!/bin/sh

ssh -o "StrictHostKeyChecking no" -i key/dillo-key.pem $USER@$HOST <<- ENDSSH
    docker kill dillo_bot

    exit
ENDSSH