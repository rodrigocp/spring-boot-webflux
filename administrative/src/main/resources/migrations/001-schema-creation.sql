--liquibase formatted sql
--changeset rodrigo.pereira:001
--comment: create database schema

create table if not exists country
(
    identifier bigserial    primary key,
    account    uuid         not null,
    created    timestamp    not null default now(),
    name       varchar(100) not null
);

create table if not exists state
(
    identifier bigserial        primary key,
    account    uuid             not null,
    created    timestamp        not null default now(),
    name       varchar(100)     not null,
    country_id bigserial        not null references country(identifier)
);

create table if not exists city
(
    identifier bigserial        primary key,
    account    uuid             not null,
    created    timestamp        not null default now(),
    name       varchar(200)     not null,
    country_id bigserial        not null references country(identifier),
    state_id   bigserial        not null references state(identifier)
);

create table if not exists holding
(
    identifier  bigserial        primary key,
    account     uuid             not null,
    created     timestamp        not null default now(),
    name        varchar(100)     not null,
    ftin        char(14)         not null,
    country_id  bigserial        not null references country(identifier),
    state_id    bigserial        not null references state(identifier),
    city_id     bigserial        not null references city(identifier)
);

create table if not exists region
(
    identifier  bigserial       primary key,
    account     uuid            not null,
    created     timestamp       not null default now(),
    name        varchar(100)    not null,
    holding_id  bigserial       not null references holding(identifier)
);

create table if not exists regionals
(
    identifier  bigserial       primary key,
    country_id  bigserial       references country(identifier),
    state_id    bigserial       references state(identifier),
    city_id     bigserial       references city(identifier),
    holding_id  bigserial       references holding(identifier),
    region_id   bigserial       references region(identifier)
);

create table if not exists "group"
(
    identifier  bigserial       primary key,
    account     uuid            not null,
    created     timestamp       not null default now(),
    name        varchar(100)    not null,
    ftin        char(14)        not null,
    holding_id  bigserial       references holding(identifier),
    regional_id bigserial       references regionals(identifier)
);

create unique index idx_country_name    on country(name);
create unique index idx_state_name      on state(country_id, name);
create unique index idx_city_name       on city(country_id, state_id, name);

create unique index idx_holding_name    on holding(state_id, city_id, name);
create unique index idx_holding_ftin    on holding(ftin);

create unique index idx_region_name     on region(holding_id, name);
create unique index idx_region_presence on regionals(country_id, state_id, city_id, holding_id, region_id);

create unique index idx_group_name      on "group"(holding_id, regional_id, name);
create unique index idx_group_ftin      on "group"(ftin);