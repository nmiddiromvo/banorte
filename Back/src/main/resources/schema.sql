-- account table
CREATE TABLE account (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    balance DECIMAL(19, 2),
    type VARCHAR(255),
    customer_id BIGINT
    --FOREIGN KEY (customer_id) REFERENCES Customer(id)
);

-- datos random
-- Insertar datos en la tabla Account
INSERT INTO account (balance, type, customer_id)
VALUES (1000.00, 'Crédito', 1);

INSERT INTO account (balance, type, customer_id)
VALUES (500.50, 'Debito', 2);

INSERT INTO account (balance, type, customer_id)
VALUES (15000.75, 'Debito', 3);

CREATE TABLE customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
);

INSERT INTO customer (id, first_name, last_name) VALUES (1, 'John', 'Doe');
INSERT INTO customer (id, first_name, last_name) VALUES (2, 'Jane', 'Smith');
INSERT INTO customer (id, first_name, last_name) VALUES (3, 'Alice', 'Johnson');


-- movement table

CREATE TABLE movement (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(19, 2) NOT NULL,
    movement_date TIMESTAMP NOT NULL,
    type VARCHAR(255) NOT NULL,
    is_favorite BOOLEAN NOT NULL,
    account_id BIGINT
   --  FOREIGN KEY (account_id) REFERENCES account(id)
);

-- insert fake movements
INSERT INTO movement (id, amount, movement_date, type, is_favorite, account_id) VALUES (1, 100.00, '2024-09-04 13:35:00', 'DEPOSIT', true, 1);
INSERT INTO movement (id, amount, movement_date, type, is_favorite, account_id) VALUES (2, 200.00, '2024-09-04 13:36:00', 'WITHDRAWAL', false, 2);
INSERT INTO movement (id, amount, movement_date, type, is_favorite, account_id) VALUES (3, 150.00, '2024-09-04 13:37:00', 'TRANSFER', true, 3);