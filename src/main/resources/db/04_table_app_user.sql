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
    CONSTRAINT app_user_fk_address FOREIGN KEY (fk_address) REFERENCES address (id)
);

INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email, is_enabled, date_added)
VALUES (3, 1, 'Alice', 'Smith', 'alicesmith', 'password789', 'alice.smith@example.com', FALSE, NOW());

INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email, is_enabled, date_added)
VALUES (1, 2, 'Bob', 'Johnson', 'bobjohnson', 'passwordabc', 'bob.johnson@example.com', TRUE, NOW());

INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email, is_enabled, date_added)
VALUES (2, NULL, 'Sarah', 'Lee', 'sarahlee', 'passworddef', 'sarah.lee@example.com', TRUE, NOW());

INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email, is_enabled, date_added)
VALUES (3, 3, 'Mike', 'Brown', 'mikebrown', 'passwordxyz', 'mike.brown@example.com', TRUE, NOW());

INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email, is_enabled, date_added)
VALUES (2, 4, 'Emily', 'Wong', 'emilywong', 'password123', 'emily.wong@example.com', FALSE, NOW());

INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email, is_enabled, date_added)
VALUES (1, NULL, 'David', 'Kim', 'davidkim', 'password456', 'david.kim@example.com', TRUE, NOW());

INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email, is_enabled, date_added)
VALUES (3, NULL, 'Julia', 'Chen', 'juliachen', 'password789', 'julia.chen@example.com', TRUE, NOW());

INSERT INTO app_user (fk_role, fk_address, first_name, last_name, username, password, email, is_enabled, date_added)
VALUES (1, 5, 'Ryan', 'Garcia', 'ryangarcia', 'passwordabc', 'ryan.garcia@example.com', FALSE, NOW());



