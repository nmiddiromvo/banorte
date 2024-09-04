CREATE TABLE transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),
    amount DOUBLE,
    dateTime TIMESTAMP,
    type VARCHAR(10),
    favorite BOOLEAN default false
);

INSERT INTO transactions (description, amount, dateTime, type) VALUES
    ('Salary Payment', 3500.00, '2024-08-22 09:15:30', 'INCOME'),
    ('Grocery Shopping', -150.75, '2024-08-23 17:30:45', 'WITHDRAW'),
    ('Electricity Bill', -120.50, '2024-08-24 10:10:20', 'WITHDRAW'),
    ('Freelance Work', 800.00, '2024-08-25 14:45:10', 'INCOME'),
    ('Restaurant Dinner', -60.00, '2024-08-26 19:20:00', 'WITHDRAW'),
    ('Gym Membership', -45.00, '2024-08-27 07:30:00', 'WITHDRAW'),
    ('Movie Tickets', -30.00, '2024-08-28 21:45:15', 'WITHDRAW'),
    ('Dividend Income', 250.00, '2024-08-29 13:00:00', 'INCOME'),
    ('Book Purchase', -20.00, '2024-08-30 11:10:30', 'WITHDRAW'),
    ('Online Course Fee', -200.00, '2024-08-31 16:05:20', 'WITHDRAW'),
    ('Gift Received', 500.00, '2024-09-01 12:00:00', 'INCOME'),
    ('Car Repair', -300.00, '2024-09-02 08:15:00', 'WITHDRAW'),
    ('Lottery Win', 1000.00, '2024-09-03 18:30:45', 'INCOME'),
    ('Coffee Shop', -10.50, '2024-09-04 08:30:00', 'WITHDRAW'),
    ('Charity Donation', -100.00, '2024-08-22 20:45:00', 'WITHDRAW'),
    ('Concert Tickets', -75.00, '2024-08-23 19:15:00', 'WITHDRAW'),
    ('Cash Deposit', 400.00, '2024-08-24 15:50:00', 'INCOME'),
    ('New Laptop', -1200.00, '2024-08-25 13:20:00', 'WITHDRAW'),
    ('Grocery Shopping', -200.50, '2024-08-26 17:30:00', 'WITHDRAW'),
    ('Stock Sale', 1500.00, '2024-08-27 14:10:00', 'INCOME');
