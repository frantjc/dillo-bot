CREATE TABLE discord_user (
  id VARCHAR(64) NOT NULL PRIMARY KEY,
  name VARCHAR(64),
  discriminator VARCHAR(4)
);

CREATE TABLE git_hub_user (
  id INTEGER NOT NULL PRIMARY KEY,
  login VARCHAR(64),
  node_id VARCHAR(64),
  avatar_url VARCHAR(128),
  gravatar_id VARCHAR(64),
  url VARCHAR(128),
  html_url VARCHAR(128),
  followers_url VARCHAR(128),
  following_url VARCHAR(128),
  gists_url VARCHAR(128),
  starred_url VARCHAR(128),
  subscriptions_url VARCHAR(128),
  organizations_url VARCHAR(128),
  repos_url VARCHAR(128),
  events_url VARCHAR(128),
  received_events_url VARCHAR(128),
  type VARCHAR(16),
  site_admin BOOLEAN
);

CREATE TABLE discord_git_hub_user (
  discord_user_id VARCHAR(64) NOT NULL,
  git_hub_user_id INTEGER NOT NULL
);