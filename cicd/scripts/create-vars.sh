#!/bin/bash

pwd
ls -al
echo ""

echo "creating var file to pass to next pipeline..."
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
docker:
        username: $DOCKER_USERNAME
        password: $DOCKER_PASSWORD
aws:
        key:
                secret: $SECRET_ACCESS_KEY
                id: $ACCESS_KEY_ID
EOF
echo "passed"