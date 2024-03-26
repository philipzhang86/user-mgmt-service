create table mwms_client
(
    id            serial
        constraint mwms_client_pk
        primary key,
    username      varchar(50) not null
        constraint mwms_client_pk_2
        unique,
    password      varchar(50) not null
        constraint mwms_client_pk_3
        unique,
    company_name  varchar(128),
    email         varchar(64),
    area_code     varchar(8),
    phone         varchar(16),
    address       varchar(128),
    city          varchar(64),
    state         varchar(32),
    country       varchar(64),
    created_by_id bigint,
    updated_by_id bigint,
    created_date  timestamp,
    updated_date  timestamp
);

alter table mwms_client
    owner to postgres;

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.mwms_client
    OWNER to postgres;