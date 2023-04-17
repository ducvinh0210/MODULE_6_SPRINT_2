drop database if exists sprint2;
create database sprint2;
 use sprint2;
 create table `account`(
 account_id int primary key auto_increment,
 usename varchar(150),
 `name` varchar(150),
 email varchar(150),
 img varchar(150),
 phone_number varchar(45),
 address varchar(150)
);
 
 create table role(
 role_id int primary key auto_increment,
 role_name varchar(150)
 );
 create table account_role(
 account_role_id int primary key auto_increment,
 account_id int ,
 role_id int ,
 foreign key(account_id)references `account`(account_id),
 foreign key(role_id)references `role`(role_id)
 );
 
 create table product_type(
 product_type_id int primary key auto_increment,
 product_type_name varchar(150));
 
 
 create table product(
 product_id int primary key	auto_increment,
 product_name varchar(150),
 product_img text,
 product_price double,
 product_description text,
flag_delete bit(1),
  product_type_id int,
  foreign key(product_type_id) references product_type(product_type_id)
  );
  
  create table `order`(
  order_id int primary key auto_increment,
  day_order varchar(45),
  account_id int ,
  foreign key(account_id) references account(account_id)
  );
  create table `order_detail`(
  order_detail_id int primary key auto_increment,
  quatity int ,
  order_id int,
  product_id int,
  foreign key(order_id) references `order`(order_id),
  foreign key(product_id) references`product`(product_id)
  );
