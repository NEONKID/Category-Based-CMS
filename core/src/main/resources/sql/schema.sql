drop table if exists category cascade;
drop table if exists post cascade;
drop table if exists tag cascade;
drop table if exists virtual_author cascade;
drop sequence if exists category_seq;
drop sequence if exists post_seq;

create sequence category_seq start 1 increment 1;
create sequence post_seq start 1 increment 1;

create table virtual_author (
    id varchar(255) not null primary key,
    name varchar(255) not null,
    description varchar(1024),
    profile_image varchar(255)
);

create table category (
    id  bigint not null primary key,
    name VARCHAR(50) not null,
    parent_id bigint references category(id)
);

create table post (
    id bigint not null primary key,
    title VARCHAR(120) not null,
    body TEXT,
    category_id bigint references category(id),
    description VARCHAR(1024),
    is_private Boolean not null,
    published_at TIMESTAMP,
    thumbnail VARCHAR(255),
    virtual_author_id VARCHAR(255) references virtual_author(id)
);

create table tag (
    name VARCHAR(50) not null primary key
);