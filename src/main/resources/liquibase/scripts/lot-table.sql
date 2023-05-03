--liquibase formatted sql
--changeset romansokolov:create_table_lot
CREATE TABLE lot(
                    id BIGSERIAL       NOT NULL PRIMARY KEY,
                    status             VARCHAR(10) NOT NULL CHECK ( status IN ('CREATED','STARTED','STOPPED')),
                    title              VARCHAR(64) NOT NULL,
                    description        VARCHAR(4096) NOT NULL,
                    start_price        INTEGER NOT NULL,
                    bid_price          INTEGER NOT NULL
);