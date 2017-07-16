create table if not exists data
(
    id int NOT NULL,
    imdb_id varchar(20) NOT NULL,
    name varchar(200),
    genres varchar(255),
    img_url varchar(1024),
    primary key(id)
);