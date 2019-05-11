
show databases;


create database db_shiro;


use db_shiro;


drop table  user;
create table user(
                     id int not null auto_increment primary key ,
                     name  varchar(255) not null unique ,
                     passwd varchar(255) not null,
                     role varchar(255) default 'user',
                     permissions json ,
                     `creat_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                     `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

);

insert user(name,passwd) values ("hcy1","123");


update user set permissions =  JSON_ARRAY('user:update') where  user.name = "hcy";
update user set permissions =  JSON_MERGE(permissions,JSON_ARRAY("aa")) where  user.name = "hcy";


select * from user;



select  JSON_MERGE(null,'["aa"]');

select JSON_ARRAY_APPEND(null,2);

update user set permissions =  json_remove(permissions,  JSON_UNQUOTE(
        JSON_SEARCH(permissions, 'one', 'aaa')
    )) where  user.name = "hcy";

select json_search(permissions,'all','aaa') from user where  user.name = "hcy";

select count(JSON_SEARCH(permissions, 'all', 'bb')) from user where name = "hcy";
