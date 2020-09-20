CREATE TABLE discord_category (
  id VARCHAR(64) NOT NULL PRIMARY KEY,
  name VARCHAR(32) NOT NULL
);

CREATE TABLE discord_server_category (
  discord_server_id VARCHAR(64) NOT NULL,
  discord_category_id VARCHAR(64) NOT NULL
);

CREATE TABLE discord_category_channel (
  discord_category_id VARCHAR(64) NOT NULL,
  discord_channel_id VARCHAR(64) NOT NULL
);
