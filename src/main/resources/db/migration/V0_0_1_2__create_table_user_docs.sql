CREATE TABLE IF NOT EXISTS user_docx
(
    id              SERIAL PRIMARY KEY,
    file_name       VARCHAR(255),
    size            INT,
    file_type       VARCHAR(10),
    file_url        VARCHAR,
    user_id         BIGINT,
    created_at      TIMESTAMP,
    last_modified   TIMESTAMP,
    UNIQUE (file_name, file_url)
);