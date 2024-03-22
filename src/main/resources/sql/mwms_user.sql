-- Table: public.mwms_user

-- DROP TABLE IF EXISTS public.mwms_user;

CREATE TABLE IF NOT EXISTS public.mwms_user
(
    id integer NOT NULL DEFAULT nextval('mwms_user_id_seq'::regclass),
    username character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(32) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(32) COLLATE pg_catalog."default" NOT NULL,
    email character varying(64) COLLATE pg_catalog."default" NOT NULL,
    created_by_id bigint,
    updated_by_id bigint,
    created_date timestamp without time zone,
    updated_date timestamp without time zone,
    CONSTRAINT mwms_user_pk PRIMARY KEY (id),
    CONSTRAINT mwms_user_pk_2 UNIQUE (username),
    CONSTRAINT mwms_user_pk_3 UNIQUE (email)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.mwms_user
    OWNER to postgres;