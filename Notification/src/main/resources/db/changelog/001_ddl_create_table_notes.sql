CREATE TABLE IF NOT EXISTS notes (
                        id serial primary key,
                        customer_id int,
                        email varchar,
                        order_id int,
                        status varchar
);
comment on table  notes is 'Таблица уведомлений';
comment on column notes.id is 'Идентификатор';
comment on column notes.customer_id is 'Владелец заказа';
comment on column notes.email is 'Почта владельца заказа';
comment on column notes.order_id is 'Номер заказа';
comment on column notes.status is 'Статус заказа';
