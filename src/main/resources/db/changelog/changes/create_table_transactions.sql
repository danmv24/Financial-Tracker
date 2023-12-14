CREATE TABLE transactions(
    transaction_id BIGSERIAL PRIMARY KEY,
    transaction_type VARCHAR(15) NOT NULL,
    category_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    comment VARCHAR(150) NOT NULL,
    date DATE NOT NULL
);