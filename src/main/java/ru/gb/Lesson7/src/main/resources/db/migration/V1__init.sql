create table if not exists students (
    id      bigserial primary key,
    name    varchar(255),
    age     int
);

insert into students (name, age)
values ('Student#1', 21),
       ('Student#2', 22),
       ('Student#3', 23);