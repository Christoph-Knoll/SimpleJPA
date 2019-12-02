set search_path to first_steps;
drop table if exists product cascade;
drop table if exists first_steps.order cascade;
drop table if exists order_item cascade;

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




