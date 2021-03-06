DROP TABLE IF EXISTS "messages";

CREATE TABLE messages
(
    id  SERIAL PRIMARY KEY,
    CONSTRAINT author FOREIGN KEY (id) REFERENCES users (id),
    CONSTRAINT chatroom FOREIGN KEY (id) REFERENCES chatrooms (id),
    text VARCHAR (100),
    date DATE,
    time TIME
);

DROP TABLE IF EXISTS chatrooms;

CREATE TABLE chatrooms
(
    id   SERIAL PRIMARY KEY,
    messages_id SERIAL NOT NULL,
    name TEXT,
    CONSTRAINT "owner" FOREIGN KEY (id) REFERENCES users (id),
    FOREIGN KEY (messages_id) REFERENCES messages (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS "users";

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    login    TEXT,
    password TEXT,
    init_id SERIAL NOT NULL,
    own_id SERIAL NOT NULL,
    FOREIGN KEY (init_id) REFERENCES chatrooms (id) ON DELETE CASCADE,
    FOREIGN KEY (own_id) REFERENCES chatrooms (id) ON DELETE CASCADE
);

