CREATE TABLE IF NOT EXISTS TPP_USER (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    account_non_expired BOOLEAN,
    account_non_locked BOOLEAN,
    credentials_non_expired BOOLEAN,
    enabled BOOLEAN
);
INSERT INTO TPP_USER (username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('user','add hashed password for testing', true, true, true, true);