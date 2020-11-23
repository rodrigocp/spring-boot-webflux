--liquibase formatted sql
--changeset rodrigo.pereira:001
--comment: Create database schema

create table if not exists "group" (
    identifier bigserial    not null,
    version    bigserial    not null,
    account    uuid         not null,
    created    timestamp    not null default now(),
    name       varchar(60)  not null,
    ein        varchar(14)  not null,
    constraint pkey_group primary key (identifier)
);

create table if not exists dealership (
    identifier bigserial    not null,
    version    bigserial    not null,
    account    uuid         not null,
    name       varchar(60)  not null,
    ein        varchar(14)  not null,
    group_id   bigserial,
    constraint pkey_dealership  primary key (identifier),
    constraint fk_group         foreign key (group_id) references "group"(identifier)
);

create table if not exists vehicle(
    identifier    bigserial    not null,
    version       bigserial    not null,
    account       uuid         not null,
    evaluator     uuid         not null,
    requester     uuid         not null,
    created       timestamp    not null default now(),
    status        varchar(20)  not null,
    plate         varchar(8)   not null,
    vin           varchar(17)  not null,
    mileage       int          not null,
    year          int          not null,
    dealership_id bigserial    not null,
    group_id      bigserial,
    constraint pkey_vehicle  primary key (identifier),
    constraint fk_dealership foreign key (dealership_id) references dealership(identifier),
    constraint fk_group      foreign key (group_id)      references "group"(identifier)
);

create unique index idx_group_ein       on "group"(ein);
create unique index idx_dealership_ein  on dealership(ein);
create unique index idx_vehicle_plate   on vehicle(identifier, version, lower(plate), dealership_id);
create unique index idx_vehicle_vin     on vehicle(identifier, version, lower(vin), dealership_id);
