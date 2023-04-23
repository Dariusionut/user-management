CREATE SEQUENCE IF NOT EXISTS seq_app_user;
CREATE TABLE IF NOT EXISTS app_user
(
    user_id    BIGINT UNIQUE      NOT NULL DEFAULT nextval('seq_app_user'),
    fk_role    BIGINT             NOT NULL DEFAULT 1,
    fk_address BIGINT             NULL UNIQUE,
    first_name VARCHAR(65)        NOT NULL,
    last_name  VARCHAR(65)        NOT NULL,
    username   VARCHAR(45) UNIQUE NOT NULL,
    password   VARCHAR(100)       NOT NULL,
    email      VARCHAR(65) UNIQUE NOT NULL,
    is_enabled BOOLEAN            NOT NULL DEFAULT TRUE,
    date_added TIMESTAMP          NOT NULL DEFAULT NOW()::TIMESTAMP,

    CONSTRAINT app_user_pk PRIMARY KEY (user_id),
    CONSTRAINT app_user_fk_role FOREIGN KEY (fk_role) REFERENCES role (role_id),
    CONSTRAINT app_user_fk_address FOREIGN KEY (fk_address) REFERENCES address (address_id)
);

INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email)
VALUES (1, NULL, 'Mary', 'Chen', 'marychen', 'password8', 'marychen@example.com');
INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email)
VALUES (2, NULL, 'John', 'Doe', 'johndoe', 'password9', 'johndoe@example.com');
INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email)
VALUES (3, 5, 'Jane', 'Doe', 'janedoe', 'password10', 'janedoe@example.com');
INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email)
VALUES (2, NULL, 'Alex', 'Lee', 'alexlee', 'password11', 'alexlee@example.com');
INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email)
VALUES (1, 7, 'Bob', 'Johnson', 'bobjohnson', 'password12', 'bobjohnson@example.com');
INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email)
VALUES (3, NULL, 'Emily', 'Wang', 'emilywang', 'password13', 'emilywang@example.com');
INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email)
VALUES (1, 3, 'David', 'Kim', 'davidkim', 'password14', 'davidkim@example.com');
INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email)
VALUES (2, NULL, 'Chris', 'Johnson', 'chrisjohnson', 'password15', 'chrisjohnson@example.com');
INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email)
VALUES (3, 9, 'Anna', 'Lee', 'annalee', 'password16', 'annalee@example.com');
INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email)
VALUES (1, NULL, 'Tom', 'Brown', 'tombrown', 'password17', 'tombrown@example.com');

