CREATE TYPE ingredient_type AS ENUM ('WRAP', 'PROTEIN', 'VEGGIES', 'CHEESE', 'SAUCE');


CREATE TABLE IF NOT EXISTS orders
(
    id              SERIAL PRIMARY KEY,
    delivery_name   varchar(50),
    delivery_street varchar(50),
    delivery_city   varchar(50),
    delivery_state  varchar(2),
    delivery_zip    varchar(10),
    cc_number       varchar(16),
    cc_expiration   varchar(5),
    cc_cvv          varchar(3),
    placed_at       timestamp


);

/*CREATE TYPE IF NOT EXISTS ingredient_type AS ENUM ('WRAP', 'PROTEIN', 'VEGGIES', 'CHEESE', 'SAUCE');*/

CREATE TABLE IF NOT EXISTS ingredient
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(25)     NOT NULL,
    type ingredient_type NOT NULL
);

CREATE TABLE IF NOT EXISTS dish
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(25) NOT NULL,
    order_id   INT,
    created_at timestamp,
    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders (id)
);

CREATE TABLE IF NOT EXISTS dish_composition
(
    dish_id       INT,
    ingredient_id INT,
    CONSTRAINT fk_dish_id FOREIGN KEY (dish_id) REFERENCES dish (id),
    CONSTRAINT fk_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredient (id)
);

