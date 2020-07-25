CREATE TABLE discord_channel (
    id VARCHAR(64) NOT NULL PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE subscription (
    id INTEGER IDENTITY PRIMARY KEY,
    subscription VARCHAR(64) NOT NULL
);

INSERT INTO subscription (
    subscription
) VALUES (
    'birthday'
);

CREATE TABLE discord_channel_subscription (
    discord_channel_id VARCHAR(64) NOT NULL,
    subscription_id INTEGER NOT NULL
);

CREATE TABLE user_details (
    id INTEGER IDENTITY PRIMARY KEY,
    discord_user_id VARCHAR(64) NOT NULL,
    birthday DATE
);

INSERT INTO user_details (
    discord_user_id,
    birthday
) VALUES (
    '310795041931264001',
    TO_DATE('1997-04-29', 'YYYY-MM-DD')
);

INSERT INTO user_details (
    discord_user_id,
    birthday
) VALUES (
    '180125051259977728',
    TO_DATE('1997-04-01', 'YYYY-MM-DD')
);

INSERT INTO user_details (
    discord_user_id,
    birthday
) VALUES (
    '548349094356320273',
    TO_DATE('1994-05-24', 'YYYY-MM-DD')
);

INSERT INTO user_details (
    discord_user_id,
    birthday
) VALUES (
    '693638325226962994',
    TO_DATE('1999-08-19', 'YYYY-MM-DD')
);

INSERT INTO user_details (
    discord_user_id,
    birthday
) VALUES (
    '474275553478836266',
    TO_DATE('1997-06-22', 'YYYY-MM-DD')
);
