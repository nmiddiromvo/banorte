CREATE TABLE transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),
    amount DOUBLE,
    dateTime TIMESTAMP,
    type VARCHAR(10),
    favorite BOOLEAN
);