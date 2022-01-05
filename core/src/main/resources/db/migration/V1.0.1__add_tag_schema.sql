CREATE TABLE IF NOT EXISTS public.tag (
    name varchar(255) not null primary key
);

CREATE TABLE IF NOT EXISTS public.tag_post (
    tag_name VARCHAR(255) NOT NULL,
    post_id BIGINT NOT NULL,
    CONSTRAINT fk_tag_name FOREIGN KEY (tag_name) REFERENCES tag(name)
);

CREATE TABLE IF NOT EXISTS public.post_tag (
    post_id BIGINT NOT NULL,
    tag_name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_post_id FOREIGN KEY (post_id) REFERENCES post(id)
);