drop table if exists customer_tbl;

create table customer_tbl (
  customer_num integer,
  name varchar(30),
  email varchar(30)
);

insert into customer_tbl values (1, 'MicroApple','sales@microapple.com');
insert into customer_tbl values (2, 'PC Parts','support@pcparts.com');
insert into customer_tbl values (3, 'Uptown PCs','admin@uptown.net');
insert into customer_tbl values (4, 'Dr. Computer','sales@drcomputer.com');
insert into customer_tbl values (5, 'Oak Computing','support@oakcomp.net');
insert into customer_tbl values (6, 'Megabytes Inc.','support@megabyte.com');

drop table if exists stock_tbl;

create table stock_tbl (
  symbol varchar(10),
  name varchar(30)
);
