INSERT INTO users(user_name, name, role, password, surname, parent_name, creation_date, last_edit_date)
values ('admin1', 'name1', 'ROLE_ADMIN', '$2a$10$eOFU9P2HsjLf6/OaxvTnpOccjouzllRk1FXJYNyZ3UEeRvo21pMCS', 'surname1', 'parentName1', '2023-01-01', null);

INSERT INTO users(user_name, name, role, password, surname, parent_name, creation_date, last_edit_date)
values ('admin2', 'name2', 'ROLE_ADMIN', '$2a$10$eOFU9P2HsjLf6/OaxvTnpOccjouzllRk1FXJYNyZ3UEeRvo21pMCS', 'surname2', 'parentName2', '2023-01-02', null);

INSERT INTO users(user_name, name, role, password, surname, parent_name, creation_date, last_edit_date)
values ('journalist3', 'name3', 'ROLE_JOURNALIST', '$2a$10$eOFU9P2HsjLf6/OaxvTnpOccjouzllRk1FXJYNyZ3UEeRvo21pMCS', 'surname3', 'parentName3', '2023-02-01', null);

INSERT INTO users(user_name, name, role, password, surname, parent_name, creation_date, last_edit_date)
values ('journalist4', 'name4', 'ROLE_JOURNALIST', '$2a$10$eOFU9P2HsjLf6/OaxvTnpOccjouzllRk1FXJYNyZ3UEeRvo21pMCS', 'surname4', 'parentName4', '2023-02-02', null);

INSERT INTO users(user_name, name, role, password, surname, parent_name, creation_date, last_edit_date)
values ('subscriber5', 'name5', 'ROLE_SUBSCRIBER', '$2a$10$eOFU9P2HsjLf6/OaxvTnpOccjouzllRk1FXJYNyZ3UEeRvo21pMCS', 'surname5', 'parentName5', '2023-03-01', null);

INSERT INTO users(user_name, name, role, password, surname, parent_name, creation_date, last_edit_date)
values ('subscriber6', 'name6', 'ROLE_SUBSCRIBER', '$2a$10$eOFU9P2HsjLf6/OaxvTnpOccjouzllRk1FXJYNyZ3UEeRvo21pMCS', 'surname6', 'parentName6', '2023-03-01', null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 1', 'News text 1', '2023-03-01', null, 4, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 2', 'News text 2', '2023-03-02', null, 3, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 3', 'News text 3', '2023-03-03', null, 1, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 4', 'News text 4', '2023-03-04', null, 2, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 5', 'News text 1', '2023-03-05', null, 4, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 6', 'News text 2', '2023-03-06', null, 3, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 7', 'News text 3', '2023-03-07', null, 1, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 8', 'News text 4', '2023-03-08', null, 2, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 9', 'News text 1', '2023-03-09', null, 4, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 10', 'News text 2', '2023-03-10', null, 3, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 11', 'News text 3', '2023-03-11', null, 1, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 12', 'News text 4', '2023-03-12', null, 2, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 13', 'News text 1', '2023-03-13', null, 4, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 14', 'News text 2', '2023-03-14', null, 3, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 15', 'News text 3', '2023-03-15', null, 1, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 16', 'News text 4', '2023-03-16', null, 2, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 17', 'News text 1', '2023-03-17', null, 4, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 18', 'News text 2', '2023-03-18', null, 3, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 19', 'News text 3', '2023-03-19', null, 1, null);

INSERT INTO news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
values ('News title 20', 'News text 4', '2023-03-20', null, 2, null);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 1', '2023-01-01', null, 1, 1);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 2', '2023-01-01', null, 2, 1);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 3', '2023-01-02', null, 5, 1);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 4', '2023-01-02', null, 6, 1);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 5', '2023-01-03', null, 1, 1);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 6', '2023-01-03', null, 2, 1);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 7', '2023-01-04', null, 5, 1);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 8', '2023-01-04', null, 6, 5);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 9', '2023-01-05', null, 1, 5);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 10', '2023-01-05', null, 2, 5);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 11', '2023-01-06', null, 5, 5);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 12', '2023-01-06', null, 6, 5);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 13', '2023-01-07', null, 1, 5);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 14', '2023-01-07', null, 2, 15);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 15', '2023-01-08', null, 5, 15);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 16', '2023-01-08', null, 6, 15);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 17', '2023-01-09', null, 1, 15);

INSERT INTO comments(text, creation_date, last_edit_date, inserted_by_id, id_news)
values ('comment 18', '2023-01-09', null, 2, 15);
