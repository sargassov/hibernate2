DROP TABLE products IF EXISTS CASCADE ;
CREATE TABLE products (id bigserial, title VARCHAR(255), cost int, PRIMARY KEY (id));
INSERT INTO products (title, cost) VALUES ('Bread', 39), ('Caviar', 539), ('Butter', 149), ('Milk', 59), ('Meat', 385);

-- DROP TABLE products IF EXISTS;
-- CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), cost int, PRIMARY KEY (id));
-- INSERT INTO products (title, cost) VALUES ('Bread', 39), ('Caviar', 539), ('Butter', 149);


DROP TABLE consumers IF EXISTS CASCADE;
CREATE TABLE consumers (id bigserial, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO consumers (name) VALUES ('Max'), ('Frank'), ('Roger')


DROP TABLE services IF EXISTS CASCADE;
CREATE TABLE services (id bigserial, consumers_id bigint REFERENCES consumers (id), products_id bigint REFERENCES products (id), time VARCHAR(255), cost int, PRIMARY KEY (id));
INSERT INTO services (consumers_id, products_id, time, cost) VALUES (1, 2, '2.2.2021', 456), (2, 4, '2.5.2021', 56),(3, 5, '4.6.2021', 300),(2, 3, '8.3.2021', 100), (1, 4, '2.2.2021', 54);

-- DROP TABLE products IF EXISTS;
-- CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), cost int, PRIMARY KEY (id));
-- INSERT INTO products (title, cost) VALUES ('Bread', 39), ('Caviar', 539), ('Butter', 149);
