-- GENERATED BY PgAdmin

-- Table: public.order

CREATE TABLE IF NOT EXISTS public."order"
(
    order_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    date date NOT NULL,
    cost double precision NOT NULL,
    CONSTRAINT order_pkey PRIMARY KEY (order_id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."order"
    OWNER to postgres;


-- Table: public.product

CREATE TABLE IF NOT EXISTS public.product
(
    product_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(16) COLLATE pg_catalog."default" NOT NULL,
    cost double precision NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (product_id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product
    OWNER to postgres;


-- Table: public.order_product

CREATE TABLE IF NOT EXISTS public.order_product
(
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    CONSTRAINT order_product_pkey PRIMARY KEY (order_id, product_id),
    CONSTRAINT order_fk FOREIGN KEY (order_id)
    REFERENCES public."order" (order_id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    NOT VALID,
    CONSTRAINT product_fk FOREIGN KEY (product_id)
    REFERENCES public.product (product_id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    NOT VALID
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.order_product
    OWNER to postgres;