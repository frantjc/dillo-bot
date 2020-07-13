#!/bin/bash

cat << EOF > vars/set_pipeline-vars.yml
concourse:
        discord:
                token: $CONCOURSE_DISCORD_TOKEN
                channel: "$CONCOURSE_DISCORD_CHANNEL"
discord:
        token: $DISCORD_TOKEN
        client:
                id: "$DISCORD_CLIENT_ID"
github:
        uri: $GITHUB_URI
        branch: $GITHUB_BRANCH
        token: $GITHUB_TOKEN
        private_key: |
                $GITHUB_PRIVATE_KEY
docker:
        email: $DOCKER_EMAIL
        username: $DOCKER_USERNAME
        password: $DOCKER_PASSWORD
EOF