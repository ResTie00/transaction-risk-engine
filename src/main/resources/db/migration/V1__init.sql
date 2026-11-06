CREATE TABLE accounts (
                          id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          external_id  VARCHAR(64) NOT NULL UNIQUE,
                          home_country VARCHAR(2)  NOT NULL,
                          created_at   TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE transactions (
                              id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                              external_id   VARCHAR(64)   NOT NULL UNIQUE,
                              account_id    BIGINT        NOT NULL REFERENCES accounts (id),
                              amount        NUMERIC(19,4) NOT NULL CHECK (amount > 0),
                              currency      VARCHAR(3)    NOT NULL,
                              merchant_name VARCHAR(255)  NOT NULL,
                              mcc           VARCHAR(4),
                              country       VARCHAR(2)    NOT NULL,
                              device_id     VARCHAR(128),
                              ip            VARCHAR(45),
                              occurred_at   TIMESTAMPTZ   NOT NULL,
                              created_at    TIMESTAMPTZ   NOT NULL DEFAULT now()
);
CREATE INDEX idx_transactions_account_time ON transactions (account_id, occurred_at DESC);

CREATE TABLE rules (
                       id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       name    VARCHAR(100) NOT NULL UNIQUE,
                       type    VARCHAR(32)  NOT NULL,
                       params  JSONB        NOT NULL DEFAULT '{}',
                       weight  INT          NOT NULL,
                       enabled BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE decisions (
                           id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           transaction_id BIGINT      NOT NULL UNIQUE REFERENCES transactions (id),
                           outcome        VARCHAR(16) NOT NULL CHECK (outcome IN ('APPROVE', 'REVIEW', 'DECLINE')),
                           score          INT         NOT NULL,
                           fired_rules    JSONB       NOT NULL DEFAULT '[]',
                           decided_at     TIMESTAMPTZ NOT NULL DEFAULT now()
);