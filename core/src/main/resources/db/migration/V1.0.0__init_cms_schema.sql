CREATE SEQUENCE IF NOT EXISTS public.category_seq start 1 increment 1;

CREATE TABLE IF NOT EXISTS public.post (
    id bigint constraint post_pk not null primary key,
    title VARCHAR(120) not null,
    body TEXT,
    description VARCHAR(1024),
    is_private Boolean not null,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    published_at TIMESTAMP,
    thumbnail VARCHAR(255)
);

create table if not exists public.post_category (
    post_id bigint NOT NULL,
    category_id bigint NOT NULL,
    constraint fk_post_id foreign key (post_id) references post (id)
);

create table if not exists public.post_virtual_author (
    post_id bigint NOT NULL,
    virtual_author_id varchar(255) NOT NULL,
    constraint fk_post_id foreign key (post_id) references post (id)
);

create table if not exists public.category (
    id  bigint constraint category_pk not null primary key default nextval('category_seq'),
    name VARCHAR(50) not null
--     parent_id bigint references category(id)
);

create table if not exists public.category_post (
    category_id bigint NOT NULL,
    post_id bigint NOT NULL,
    constraint fk_category_id foreign key (category_id) references category (id)
);

create table if not exists public.virtual_author (
    id varchar(255) not null primary key,
    name varchar(255) not null,
    profile_image varchar(255)
);

create table if not exists public.virtual_author_post (
    virtual_author_id varchar(255) NOT NULL,
    post_id bigint NOT NULL,
    constraint fk_virtual_author_id foreign key (virtual_author_id) references virtual_author(id)
);