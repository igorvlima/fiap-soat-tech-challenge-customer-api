CREATE SEQUENCE customer_id_seq START 1;

CREATE TABLE customer (
                          id BIGINT DEFAULT nextval('customer_id_seq') PRIMARY KEY,
                          cpf VARCHAR(255) NOT NULL,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          active BOOLEAN NOT NULL,
                          created_at TIMESTAMP NOT NULL,
                          updated_at TIMESTAMP
);