CREATE TABLE IF NOT EXISTS menu (
                        id serial primary key,
                        dish_name varchar unique,
                        dish_price real,
                        status int
);
comment on table  menu is 'Меню';
comment on column menu.id is 'Идентификатор блюда';
comment on column menu.dish_name is 'Наименование блюда';
comment on column menu.dish_price is 'Стоимость блюда';
comment on column menu.status is 'Статус доступности блюда';
