CREATE TABLE users (
     id BIGINT auto_increment primary key,
     user_name varchar(40),
     role varchar,
     password varchar,
     name varchar(20),
     surname varchar(20),
     parent_name varchar(20),
     creation_date date,
     last_edit_date date
);

CREATE TABLE news (
     id BIGINT auto_increment primary key,
     title varchar(150),
     text varchar(2000),
     creation_date date,
     last_edit_date date,
     inserted_by_id BIGINT NULL DEFAULT NULL,
     updated_by_id BIGINT NULL DEFAULT NULL,
     constraint fk_news_inserted_user foreign key (inserted_by_id) references users(id),
     constraint fk_news_updated_user foreign key (updated_by_id) references users(id)
);

CREATE TABLE comments (
     id BIGINT auto_increment primary key,
     text varchar(300),
     creation_date date,
     last_edit_date date,
     inserted_by_id INTEGER,
     id_news INTEGER,
     constraint fk_comments_inserted_user foreign key (inserted_by_id) references users(id),
     constraint fk_comments_news foreign key (id_news) references news(id)
);