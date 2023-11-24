CREATE TABLE transactions(
    transaction_id BIGSERIAL PRIMARY KEY,
    transaction_type VARCHAR(15) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL
);