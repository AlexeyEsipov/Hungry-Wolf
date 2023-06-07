CREATE TABLE IF NOT EXISTS cards (
                        id serial primary key,
                        card_name varchar,
                        rate int,
                        customer_id int
);
comment on table  cards is 'Таблица клиентов';
comment on column cards.id is 'Номер карты';
comment on column cards.card_name is 'Наименование карты';
comment on column cards.customer_id is 'Идентификатор хозяина карты';
