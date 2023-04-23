CREATE SEQUENCE IF NOT EXISTS seq_address;
CREATE TABLE IF NOT EXISTS address
(
    id                 BIGINT      NOT NULL DEFAULT NEXTVAL('seq_address'),
    country            VARCHAR(45) NOT NULL,
    city               VARCHAR(45) NOT NULL,
    door_number        VARCHAR(10) NOT NULL,
    additional_details VARCHAR(65) NULL,
    CONSTRAINT address_pk PRIMARY KEY (id)
);
