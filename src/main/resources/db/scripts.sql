CREATE TABLE IF NOT EXISTS role
(
    role_id   BIGINT UNIQUE      NOT NULL,
    role_name VARCHAR(45) UNIQUE NOT NULL,

    CONSTRAINT role_pk PRIMARY KEY (role_id)
);

INSERT INTO role(role_id, role_name)
VALUES (1, 'USER');
INSERT INTO role(role_id, role_name)
VALUES (2, 'VISITOR');
INSERT INTO role(role_id, role_name)
VALUES (3, 'ADMIN');

CREATE SEQUENCE seq_app_user;
CREATE TABLE IF NOT EXISTS app_user
(
    user_id    BIGINT UNIQUE NOT NULL DEFAULT nextval('seq_app_user'),
    fk_role    BIGINT        NOT NULL DEFAULT 1,
    name       VARCHAR(65)   NOT NULL,
    username   VARCHAR(45),
    password   VARCHAR(100),
    date_added TIMESTAMP     NOT NULL DEFAULT NOW()::TIMESTAMP,

    CONSTRAINT app_user_pk PRIMARY KEY (user_id),
    CONSTRAINT app_user_fk_role FOREIGN KEY (fk_role) REFERENCES role (role_id)
);

INSERT INTO app_user(name, username, password)
VALUES ('Mike', 'MikeUser', 'pass123');
INSERT INTO app_user(name, username, password)
VALUES ('Alex', 'AlexUser', 'pass123');
INSERT INTO app_user(name, username, password)
VALUES ('Andreea', 'AndreeaUser', 'pass123');
INSERT INTO app_user(fk_role, name, username, password)
VALUES (2, 'John', 'MikeUser', 'pass123');
INSERT INTO app_user(fk_role, name, username, password)
VALUES (3, 'Jane', 'MikeUser', 'pass123');
INSERT INTO app_user(name, username, password)
VALUES ('Elena', 'ElenaUser', 'pass123');
INSERT INTO app_user(fk_role,name, username, password)
VALUES (2, 'David', 'DavUser', 'pass123');