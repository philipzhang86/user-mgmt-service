
create table mwms_staff
(
    id           serial
        constraint mwms_staff_pk
        primary key,
    username     varchar(64)  not null
        constraint mwms_staff_pk_2
        unique,
    password     varchar(64)  not null,
    email        varchar(128) not null
        constraint mwms_staff_pk_3
        unique,
    role         varchar(32)  not null,
    created_date timestamp,
    updated_date timestamp
);

alter table mwms_staff
    owner to postgres;

