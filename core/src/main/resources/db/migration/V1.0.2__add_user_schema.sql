create table if not exists public.post_user (
    post_id bigint NOT NULL,
    user_id bigint NOT NULL,
    constraint fk_post_id foreign key (post_id) references post (id)
);

create table if not exists public.users (
    id bigint NOT NULL PRIMARY KEY,
    email varchar(128) NOT NULL,
    nickname varchar(20) NOT NULL
);

create table if not exists public.user_post (
    user_id bigint NOT NULL,
    post_id bigint NOT NULL,
    constraint fk_user_id foreign key (user_id) references users (id)
);