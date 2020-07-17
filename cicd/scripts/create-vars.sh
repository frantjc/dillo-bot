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
        s3:
                key:
                        secret: $S3_SECRET_ACCESS_KEY
                        id: $S3_ACCESS_KEY_ID
        ec2:
                user: $EC2_USER
                public_dns: $EC2_PUBLIC_DNS
                key: |
                        $EC2_KEY
EOF
echo "passed"