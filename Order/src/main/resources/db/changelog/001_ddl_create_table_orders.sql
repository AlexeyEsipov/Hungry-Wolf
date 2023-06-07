CREATE TABLE IF NOT EXISTS orders (
                        id serial primary key,
                        customer_db_id int,
                        created timestamp,
                        modify timestamp,
                        status int
);
comment on table  orders is 'Таблица заказов';
comment on column orders.id is 'Идентификатор';
comment on column orders.customer_db_id is 'Указатель на владельца заказа';
comment on column orders.created is 'Дата создания заказа';
comment on column orders.modify is 'Дата модификации заказа';
comment on column orders.status is 'Код статуса заказа';
