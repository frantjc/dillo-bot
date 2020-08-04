CREATE TABLE discord_channel (
    id VARCHAR(64) NOT NULL PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE subscription (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    subscription VARCHAR(64) NOT NULL
);

INSERT INTO subscription (
    subscription
) VALUES (
    'BIRTHDAY'
);

CREATE TABLE discord_channel_subscription (
    discord_channel_id VARCHAR(64) NOT NULL,
    subscription_id INTEGER NOT NULL
);

CREATE TABLE user_details (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
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

INSERT INTO user_details (
    discord_user_id,
    birthday
) VALUES (
    '276428192259112973',
    TO_DATE('1996-07-25', 'YYYY-MM-DD')
);

INSERT INTO user_details (
    discord_user_id,
    birthday
) VALUES (
    '186574670902853633',
    TO_DATE('2000-01-04', 'YYYY-MM-DD')
);

INSERT INTO user_details (
    discord_user_id,
    birthday
) VALUES (
    '252536561441177602',
    TO_DATE('2000-04-21', 'YYYY-MM-DD')
);

INSERT INTO user_details (
    discord_user_id,
    birthday
) VALUES (
    '318920529988026368',
    TO_DATE('1993-08-05', 'YYYY-MM-DD')
);

INSERT INTO user_details (
    discord_user_id,
    birthday
) VALUES (
    '318105129519808512',
    TO_DATE('1997-04-15', 'YYYY-MM-DD')
);
