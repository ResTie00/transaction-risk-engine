INSERT INTO rules (name, type, params, weight, enabled)
VALUES ('High amount (USD)', 'AMOUNT_THRESHOLD',
        '{"threshold": 10000, "currency": "USD"}', 50, TRUE);