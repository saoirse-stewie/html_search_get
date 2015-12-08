use address;

drop table if exists person;

create table person(
  id int ,
  firstname VARCHAR(30),
  lastname VARCHAR(30),
  address VARCHAR(30),
  phone int,
   primary key (id));


insert into person (id,firstname,lastname,address,phone) values (1, 'sarah', 'jane','henry street', 5555)
;
insert into person (id, firstname, lastname, address, phone) VALUES (2, 'sarah', 'jane', 'henry street', 5555)
;
insert into person (id, firstname, lastname, address, phone) VALUES (3, 'sarah', 'jane', 'henry street', 5555)
;
insert into person (id, firstname, lastname, address, phone) VALUES (4, 'sarah', 'jane', 'henry street', 5555)
;
insert into person (id, firstname, lastname, address, phone) VALUES (5, 'tom', 'cleary', 'henry poo', 7777)
;
