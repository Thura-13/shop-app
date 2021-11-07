-- for category table

insert into category (category_name) values ('Cake');
insert into category (category_name) values ('Drink');
insert into category (category_name) values ('Fruit');


-- for product table

insert into product(category_id,product_name,price,weight,description,image_name)
values (1,'Birthday Cake',3000,4.5,'Good','cake_1.jpg');
insert into product (category_id,product_name,price,weight,description,image_name)
values (1,'Chocolate Cake',5000,3.5,'Good','cake_2.jpg');
insert into product (category_id,product_name,price,weight,description,image_name)
values (1,'Caramello Cake',4500,5.5,'Good','cake_3.jpg');

insert into product (category_id,product_name,price,weight,description,image_name)
values (2,'Healthy Drink',2000,4.5,'Good','drink_1.jpg');
insert into product (category_id,product_name,price,weight,description,image_name)
values (2,'Healthy Drink',1500,3.5,'Good','drink_2.jpg');
insert into product (category_id,product_name,price,weight,description,image_name)
values (2,'Healthy Drink',3000,5.5,'Good','drink_3.jpg');

insert into product (category_id,product_name,price,weight,description,image_name)
values (3,'Red Dragon',1000,4.5,'Good','fruit_1.jpg');
insert into product (category_id,product_name,price,weight,description,image_name)
values (3,'Apple',1500,3.5,'Good','fruit_2.jpg');
insert into product (category_id,product_name,price,weight,description,image_name)
values (3,'Orange',2500,5.5,'Good','fruit_3.jpg');







