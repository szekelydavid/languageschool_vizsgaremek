drop table if exists course CASCADE;
drop table if exists language CASCADE;
drop table if exists language_teachers CASCADE;
drop table if exists teacher CASCADE;
drop table if exists teacher_languages CASCADE;


create table course
(
    id         int8 generated by default as identity,
    end_date   varchar(255),
    name       varchar(255),
    start_date varchar(255),
    teacher_id int8,
    primary key (id)
);

create table language
(
    id   int8 generated by default as identity,
    name varchar(255),
    primary key (id)
);


create table language_teachers
(
    language_id int8 not null,
    teachers_id int8 not null
);

create table teacher
(
    id      int8 generated by default as identity,
    address varchar(255),
    age     int4 not null,
    name    varchar(255),
    primary key (id)
);

create table teacher_languages
(
    teacher_id   int8 not null,
    languages_id int8 not null
);

alter table if exists course
    add constraint FKsybhlxoejr4j3teomm5u2bx1n
        foreign key (teacher_id)
            references teacher

alter table if exists language_teachers
    add constraint FKhtbnuwmdu8o3saya0dxk9xile
        foreign key (teachers_id)
            references teacher;

alter table if exists language_teachers
    add constraint FKrpfgadcc29wdjs4vdcjyu892v
        foreign key (language_id)
            references language;

alter table if exists teacher_languages
    add constraint FKmwjg10vbgr2reu74x7dshpe4n
        foreign key (languages_id)
            references language;

alter table if exists teacher_languages
    add constraint FKkgktjh2hnfw3i7jvwocu77hs6
        foreign key (teacher_id)
            references teacher;
