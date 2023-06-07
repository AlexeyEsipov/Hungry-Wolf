CREATE TABLE order_products (
    kitchen_order_id INTEGER NOT NULL,
    quantity         INTEGER,
    product_name     VARCHAR(255) NOT NULL,
    CONSTRAINT pk_order_products PRIMARY KEY (kitchen_order_id, product_name)
);

ALTER TABLE order_products
    ADD CONSTRAINT fk_order_products_on_kitchen_order
        FOREIGN KEY (kitchen_order_id) REFERENCES kitchen_orders (id);
