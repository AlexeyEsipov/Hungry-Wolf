CREATE TABLE IF NOT EXISTS customers (
                        id serial primary key,
                        customer_name varchar,
                        customer_email varchar,
                        status int
);
comment on table  customers is 'Таблица клиентов';
comment on column customers.id is 'Идентификатор';
comment on column customers.customer_name is 'Наименование клиента';
comment on column customers.customer_email is 'Электронная почта клиента';
comment on column customers.status is 'Статус активности клиента';
