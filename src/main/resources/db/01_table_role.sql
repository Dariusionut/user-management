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
