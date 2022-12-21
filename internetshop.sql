CREATE DATABASE internet_shop
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
	
CREATE TABLE IF NOT EXISTS public.cart
(
    id bigint NOT NULL,
    product_id bigint NOT NULL,
    product_title character varying COLLATE pg_catalog."default" NOT NULL,
    amount integer,
    price integer NOT NULL,
    CONSTRAINT "Cart_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cart
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.category
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    title character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (id),
    CONSTRAINT category_category_key UNIQUE (title)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.category
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.delivery_adress
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    user_id bigint NOT NULL,
    adress character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT delivery_adress_pkey PRIMARY KEY (id),
    CONSTRAINT delivery_adress_adress_key UNIQUE (adress),
    CONSTRAINT delivery_adress_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.delivery_adress
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.orders
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    total_price integer,
    username character varying COLLATE pg_catalog."default",
    CONSTRAINT orders_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.orders
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.orders_items
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    order_id bigint,
    product_id bigint,
    amount integer,
    price integer,
    CONSTRAINT orders_items_pkey PRIMARY KEY (id),
    CONSTRAINT orders_items_order_id_fkey FOREIGN KEY (order_id)
    REFERENCES public.orders (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID,
    CONSTRAINT orders_items_product_id_fkey FOREIGN KEY (product_id)
    REFERENCES public.product (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.orders_items
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS public.product
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    title character varying(100) COLLATE pg_catalog."default" NOT NULL,
    price integer NOT NULL,
    category_id bigint NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id),
    CONSTRAINT product_title_key UNIQUE (title),
    CONSTRAINT product_category_id_fkey FOREIGN KEY (category_id)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.roles
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id),
    CONSTRAINT roles_name_key UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.roles
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    username character varying(30) COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_username_key UNIQUE (username)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS public.users_personal_data
(
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    first_name character varying(150) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(150) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_personal_data_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users_personal_data
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS public.users_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT users_roles_role_id_fkey FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT users_roles_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users_roles
    OWNER to postgres;

INSERT INTO public.category (title) VALUES
('Напитки'),
('Хлебобулочные'),
('Рыба'),
('Мясо и колбасы'),
('Кркпы и макароны'),
('Овощи и фрукты'),
('Молочные продукты'),
('Сыр'),
('Кондитерские изделия'),
('Прочее');

INSERT INTO public.product (title,price,category_id) VALUES
('Селедка', 500,3),
('Лимонад', 55, 1),
('Молоко', 59, 7),
('Кефир', 60, 7),
('Огурчики', 100, 6),
('Хлеб', 35, 2),
('Сметана', 85, 7),
('Оленина', 2500, 4),
('Йогурт', 55, 7),
('Мандарины', 200, 6),
('Макароны', 70, 5),
('Гречка', 95, 5),
('Квас', 75, 1),
('Сливки', 100, 7),
('Пончики', 65, 2),
('Конфеты', 600, 9),
('Зефир', 150, 9),
('Мармелад', 500, 9),
('Капуста', 5, 6),
('Крекер', 54. 9),
('Манка', 75, 5),
('Булгур', 95, 5);


INSERT INTO public.roles (name) VALUES
('ROLE_ROOT'),
('ROLE_ADMIN'),
('ROLE_MANAGER'),
('ROLE_USER');

INSERT INTO public.users (username, password, email) VALUES
('serg', '$2y$10$5jEmti18CIGrQvZjMyOqpew4b4zvR3DXKu4BQl5ZsKsob7g5NONIe','livegrafics@rambler.ru'),
('nata', '$2y$10$5jEmti18CIGrQvZjMyOqpew4b4zvR3DXKu4BQl5ZsKsob7g5NONIe','livegrafics@mail.ru');

INSERT INTO public.users_roles (user_id, role_id) VALUES
(1, 2),
(2,4);

