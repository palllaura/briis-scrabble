CREATE DATABASE briis;

create table Word
(
    id        serial primary key,
    word_value     text unique  not null
);