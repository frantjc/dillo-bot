CREATE TABLE discord_server (
  id VARCHAR(64) NOT NULL PRIMARY KEY,
  name VARCHAR(32) NOT NULL,
  description VARCHAR(256),
  region VARCHAR(16),
  icon_url VARCHAR(128),
  owner_id VARCHAR(64),
  afk_channel_id VARCHAR(64),
  default_channel_id VARCHAR(64),
  system_channel_id VARCHAR(64)
);

CREATE TABLE discord_server_member (
  id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  discord_server_id VARCHAR(64) NOT NULL,
  discord_user_id VARCHAR(64) NOT NULL
);

CREATE TABLE discord_server_channel (
  discord_server_id VARCHAR(64) NOT NULL,
  discord_channel_id VARCHAR(64) NOT NULL
);
