CREATE TABLE IF NOT EXISTS RECIPIENT_BANK_ACCOUNT (
    id SERIAL PRIMARY KEY,
    routing_number VARCHAR(255) NOT NULL,
    account_number VARCHAR(255) NOT NULL,
    bank_name VARCHAR(255) NOT NULL,
    holder_first_name VARCHAR(255) NOT NULL,
    holder_last_name VARCHAR(255) NOT NULL,
    holder_national_identification_number BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    currency VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS WALLET_BANK_ACCOUNT (
    id SERIALT PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    account_number VARCHAR(255) NOT NULL,
    currency VARCHAR(255) NOT NULL,
    routing_number VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS WALLET_TRANSACTION (
  id VARCHAR(255) PRIMARY KEY,
  user_id INT NOT NULL,
  amount DOUBLE NOT NULL,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  transaction_type VARCHAR(255) NOT NULL,
  wallet_bank_account_id BIGINT NOT NULL,
  FOREIGN KEY (wallet_bank_account_id) REFERENCES wallet_bank_account (id)
);

CREATE TABLE  IF NOT EXISTS PAYMENT (
    id SERIAL PRIMARY KEY,
    transaction_id VARCHAR(255),
    recipient_bank_account_id BIGINT,
    user_id BIGINT NOT NULL,
    amount DOUBLE,
    fee DOUBLE,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    wallet_transaction_id VARCHAR(255),
    FOREIGN KEY (recipient_bank_account_id) REFERENCES recipient_bank_account(id),
    FOREIGN KEY (wallet_transaction_id) REFERENCES wallet_transaction(id)
);

CREATE TABLE  IF NOT EXISTS PAYMENT_STATE (
    id SERIAL PRIMARY KEY,
    payment_id BIGINT,
    status VARCHAR(255) NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (payment_id) REFERENCES payment(id)
);

