CREATE TABLE IF NOT EXISTS products (
                        id serial primary key,
                        product_name varchar
);
comment on table  products is 'Таблица продуктов';
comment on column products.id is 'Идентификатор';
comment on column products.product_name is 'Наименование продукта';
