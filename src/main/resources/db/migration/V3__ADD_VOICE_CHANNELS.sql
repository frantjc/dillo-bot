ALTER TABLE discord_channel
ADD COLUMN type VARCHAR(32);

UPDATE discord_channel
SET type = 'text';

ALTER TABLE discord_channel
ALTER COLUMN type SET NOT NULL;
