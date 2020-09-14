CREATE TABLE discord_role (
  id VARCHAR(64) NOT NULL PRIMARY KEY,
  name VARCHAR(32) NOT NULL,
  color VARCHAR(32)
);

CREATE TABLE discord_server_role (
  discord_server_id VARCHAR(64) NOT NULL,
  discord_role_id VARCHAR(64) NOT NULL
);

CREATE TABLE discord_server_member_role (
  discord_server_member_id INTEGER NOT NULL,
  discord_role_id VARCHAR(64) NOT NULL
);
