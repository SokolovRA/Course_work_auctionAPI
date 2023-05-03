--liquibase formatted sql
--changeset romansokolov:create_table_bid
CREATE TABLE bid(
    id BIGSERIAL       NOT NULL PRIMARY KEY,
    bidder_name         VARCHAR(64) NOT NULL,
    bid_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    lot_id BIGINT NOT NULL REFERENCES lot(id)
);