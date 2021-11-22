CREATE TABLE IF NOT EXISTS PRICES (
    id INTEGER NOT NULL AUTO_INCREMENT,
    brandid INTEGER NOT NULL,
    startdate TIMESTAMP NOT NULL,
    enddate TIMESTAMP NOT NULL,
    pricelist INTEGER NOT NULL,
    productid BIGINT NOT NULL,
    priority INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(5) NOT NULL,
    PRIMARY KEY (id)
);