create schema if not exists netology;

create table if not exists netology.user
(
    id       integer generated by default as identity constraint user_pk primary key,
    username varchar(300) not null,
    password text         not null
);

create table if not exists netology.tfile
(
    id          integer generated by default as identity
        constraint tfile_pk
        primary key,
    filename    varchar(255) not null,
    create_date timestamp,
    size        bigint,
    file        bytea not null,
    user_id     integer
        constraint tfile_user_null_fk
            references netology.user (id)
);

INSERT INTO netology.user (username, password)
VALUES ('koko', '$2a$11$iJhjSfb.KJLmmPpwi7dMluby/kTSTHZo4jXFWdzLr2848TuPlex0K');