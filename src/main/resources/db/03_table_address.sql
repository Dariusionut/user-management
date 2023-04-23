CREATE SEQUENCE IF NOT EXISTS seq_address;
CREATE TABLE IF NOT EXISTS address
(
    address_id BIGINT UNIQUE NOT NULL DEFAULT nextval('seq_address'),
    street     VARCHAR(100)  NULL,
    city       VARCHAR(100)  NOT NULL,
    state      VARCHAR(100)  NOT NULL,
    zip        VARCHAR(20)   NOT NULL,

    CONSTRAINT address_pk PRIMARY KEY (address_id)
);
INSERT INTO address (street, city, state, zip)
VALUES ('Strada Libertatii', 'Bucuresti', 'Bucuresti', '010663'),
       ('Bulevardul Unirii', 'Cluj-Napoca', 'Cluj', '400010'),
       ('Bulevardul Corneliu Coposu', 'Timisoara', 'Timis', '300283'),
       ('Strada General Traian Mosoiu', 'Oradea', 'Bihor', '410181'),
       ('Bulevardul Regina Maria', 'Iasi', 'Iasi', '700506'),
       ('Strada Vasile Lascar', 'Botosani', 'Botosani', '710233'),
       ('Strada Horea', 'Alba Iulia', 'Alba', '510011'),
       ('Bulevardul Eroilor de la Tisa', 'Satu Mare', 'Satu Mare', '440021'),
       ('Strada Alexandru Vaida-Voevod', 'Sibiu', 'Sibiu', '550182'),
       ('Strada Traian Vuia', 'Arad', 'Arad', '310130');