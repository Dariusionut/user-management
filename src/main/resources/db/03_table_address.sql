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
INSERT INTO address (country, city, door_number, additional_details)
VALUES ('USA', 'New York City', '1234', 'Apartment 5B');
INSERT INTO address (country, city, door_number, additional_details)
VALUES ('UK', 'London', '21', '3rd Floor');
INSERT INTO address (country, city, door_number, additional_details)
VALUES ('Australia', 'Sydney', '22', 'Beachfront');
INSERT INTO address (country, city, door_number, additional_details)
VALUES ('USA', 'Colorado', '54', 'Mountain View');
INSERT INTO address (country, city, door_number, additional_details)
VALUES ('UAE', 'Dubai', '1001', 'Luxury Living');
INSERT INTO address (country, city, door_number, additional_details)
VALUES ('Italy', 'Tuscany', '13', 'Vineyard View');
INSERT INTO address (country, city, door_number, additional_details)
VALUES ('Netherlands', 'Amsterdam', '33', 'Canal View');
INSERT INTO address (country, city, door_number, additional_details)
VALUES ('USA', 'Miami', '456', 'Oceanfront');
INSERT INTO address (country, city, door_number, additional_details)
VALUES ('France', 'French Alps', '7', 'Ski In/Out');
