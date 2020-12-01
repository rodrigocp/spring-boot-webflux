--liquibase formatted sql
--changeset rodrigo.pereira:001
--comment: Create database schema

create table if not exists country
(
    identifier bigserial    not null,
    version    bigserial    not null,
    account    uuid         not null,
    created    timestamp    not null default now(),
    name       varchar(100) not null,
    constraint pkey_country primary key (identifier)
);

create table if not exists province
(
    identifier bigserial        not null,
    version    bigserial        not null,
    account    uuid             not null,
    created    timestamp        not null default now(),
    name       varchar(100)     not null,
    country_id bigserial        not null,
    constraint pkey_province    primary key (identifier),
    constraint fk_group         foreign key (country_id) references country(identifier)
);

create table if not exists subregion
(
    identifier  bigserial       not null,
    version     bigserial       not null,
    account     uuid            not null,
    created     timestamp       not null default now(),
    name       varchar(100)     not null,
    country_id  bigserial       not null,
    province_id bigserial       not null,
    constraint pkey_subregion   primary key (identifier),
    constraint fk_group         foreign key (country_id)    references country(identifier),
    constraint fk_provice       foreign key (province_id)   references province(identifier)
);

create unique index idx_country_name    on country(name);
create unique index idx_province_name   on province(name);
create unique index idx_subregion_name  on subregion(name);