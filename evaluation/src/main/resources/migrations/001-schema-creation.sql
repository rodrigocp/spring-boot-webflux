--liquibase formatted sql
--changeset rodrigo.pereira:001
--comment: Create schema

create table if not exists vehicle(
    identifier bigserial not null,
    version bigserial not null,
    created timestamp not null default now(),
    status varchar(16) default null,
    constraint pkey_vehicle primary key (identifier)
);