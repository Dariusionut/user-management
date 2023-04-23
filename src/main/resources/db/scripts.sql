DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS role;
DROP SEQUENCE IF EXISTS seq_app_user;

CREATE TABLE IF NOT EXISTS role
(
    role_id   BIGINT UNIQUE      NOT NULL,
    role_name VARCHAR(45) UNIQUE NOT NULL,

    CONSTRAINT role_pk PRIMARY KEY (role_id)
);

INSERT INTO role(role_id, role_name)
VALUES (1, 'USER')
ON CONFLICT (role_id) DO NOTHING;

INSERT INTO role(role_id, role_name)
VALUES (2, 'VISITOR')
ON CONFLICT (role_id) DO NOTHING;

INSERT INTO role(role_id, role_name)
VALUES (3, 'ADMIN')
ON CONFLICT (role_id) DO NOTHING;

CREATE SEQUENCE if not exists seq_app_user;
CREATE TABLE IF NOT EXISTS app_user
(
    user_id    BIGINT UNIQUE      NOT NULL DEFAULT nextval('seq_app_user'),
    fk_role    BIGINT             NOT NULL DEFAULT 1,
    first_name VARCHAR(65)        NOT NULL,
    last_name  VARCHAR(65)        NOT NULL,
    username   VARCHAR(45) UNIQUE NOT NULL,
    password   VARCHAR(100)       NOT NULL,
    email      VARCHAR(65) UNIQUE NOT NULL,
    is_enabled BOOLEAN            NOT NULL DEFAULT TRUE,
    date_added TIMESTAMP          NOT NULL DEFAULT NOW()::TIMESTAMP,

    CONSTRAINT app_user_pk PRIMARY KEY (user_id),
    CONSTRAINT app_user_fk_role FOREIGN KEY (fk_role) REFERENCES role (role_id)
);

INSERT INTO app_user(first_name, last_name, username, password, email)
VALUES ('Mike', 'Traktor', 'MikeUser', 'pass123', 'mike@email.com');
INSERT INTO app_user(first_name, last_name, username, password, email)
VALUES ('Alex', 'Committer', 'AlexUser', 'pass123', 'alex@email.com');
INSERT INTO app_user(first_name, last_name, username, password, email)
VALUES ('Andreea', 'TalkToUS', 'AndreeaUser', 'pass123', 'andreea@email.com');
INSERT INTO app_user(fk_role, first_name, last_name, username, password, email)
VALUES (2, 'John', 'Doe', 'JohnUser', 'pass123', 'john@email.com');
INSERT INTO app_user(fk_role, first_name, last_name, username, password, email)
VALUES (3, 'Jane', 'Dow', 'JaneUser', 'pass123', 'jane@email.com');
INSERT INTO app_user(first_name, last_name, username, password, email)
VALUES ('Elena', 'Tintiri', 'ElenaUser', 'pass123', 'elena@email.com');
INSERT INTO app_user(fk_role, first_name, last_name, username, password, email)
VALUES (2, 'David', 'Beckham', 'DavUser', 'pass123', 'david@email.com');

CREATE TABLE IF NOT EXISTS address
(
    id                 BIGINT      NOT NULL DEFAULT NEXTVAL('seq_address'),
    country            VARCHAR(45) NOT NULL,
    city               VARCHAR(45) NOT NULL,
    door_number        VARCHAR(10) NOT NULL,
    additional_details VARCHAR(65) NULL,
    PRIMARY KEY (id)
);
CREATE SEQUENCE IF NOT EXISTS seq_address;

ALTER TABLE app_user
    ADD COLUMN fk_address BIGINT UNIQUE,
    ADD CONSTRAINT fk_address
        FOREIGN KEY (fk_address)
            REFERENCES address (id);