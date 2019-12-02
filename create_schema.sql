drop schema first_steps cascade;
create schema first_steps;
set search_path to first_steps;

-- Tables 1st exercise
create table person (
    ssn varchar(10) not null,
    date_of_birth date not null,
    first_name varchar(20) null,
    last_name varchar(30) null,
    money numeric(8, 2),
    constraint pk_person primary key (ssn)
);
create table address (
    ssn varchar(10) not null,
    address_no smallint not null,
    country varchar(20) null,
    city varchar(20) null,
    street varchar(30) null,
    street_no smallint null,
    constraint pk_address primary key (ssn, address_no),
    constraint fk_address_person foreign key (ssn) references person (ssn) on delete cascade
);
alter table person
    add is_awesome boolean not null default false,
    add awesomeness real null,
    add wealth numeric(8, 2) null;

insert into person (ssn, date_of_birth, first_name, last_name, wealth)
values ('1234010180','1980-01-01','Horst','Fischer', 22856.23),
       ('4321020290','1990-02-02','Ursula', 'Bauer', 895621.86);
insert into address (ssn, address_no, country, city, street, street_no)
values ('1234010180', 1, 'Austria', 'Wels', 'Hauptstraße', 4),
       ('4321020290', 1, 'Austria', 'Leonding', 'Limesstr.', 12),
       ('4321020290', 2, 'Austria', 'Linz', 'Domstrasse.', 61);

select p.*, a.city, a.street, a.street_no from person p left outer join address a on p.ssn = a.ssn;


-- Tables 2nd Exercise
create table product
(
	product_id int
		constraint product_pk
			primary key,
	Description varchar,
	Price numeric
);

create table first_steps.order
(
	order_id int
		constraint order_pk
			primary key,
	ssn varchar not null,
    address_no smallint unique not null,
	order_date date not null,
	order_state smallint not null,
	constraint order_address_fk foreign key (ssn, address_no) references address (ssn, address_no)
);

create table first_steps.order_item
(
	order_id int
		constraint order_item_order_order_id_fk
			references first_steps.order (order_id),
	product_id int
		constraint order_item_product_product_id_fk
			references product (product_id),
	amount int not null,
	constraint order_item_pk primary key (order_id, product_id)
);
