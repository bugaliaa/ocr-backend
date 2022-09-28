CREATE TABLE IF NOT EXISTS user_otp
(
    contact_number       VARCHAR(10)  NOT NULL,
    country_code         SMALLINT NOT NULL,
    latest_otp           VARCHAR(6)   NOT NULL,
    latest_otp_timestamp TIMESTAMP NOT NULL,
    PRIMARY KEY (contact_number, country_code)
)