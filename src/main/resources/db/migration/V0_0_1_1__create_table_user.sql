CREATE TABLE IF NOT EXISTS users
(
    id                   SERIAL PRIMARY KEY,
    first_name           VARCHAR(255),
    last_name            VARCHAR(255),
    country              VARCHAR(100) NOT NULL,
    contact_number       VARCHAR(10)  NOT NULL,
    email                VARCHAR(254),
    role                 VARCHAR(20) NOT NULL,
    created_at           TIMESTAMP,
    last_modified        TIMESTAMP,
    UNIQUE (country, contact_number)
);