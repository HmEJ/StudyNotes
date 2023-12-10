create database tb_test;
use tb_test;

create table tb_customer
(
    customer_id    int auto_increment
        primary key,
    customer_name  varchar(50)   null,
    customer_tel   varchar(20)   null,
    customer_point int default 0 null
);

INSERT INTO db_test.tb_customer (customer_id, customer_name, customer_tel, customer_point) VALUES (1, '张三', '00-12345678', 0);
INSERT INTO db_test.tb_customer (customer_id, customer_name, customer_tel, customer_point) VALUES (2, '李四', '00-12223335', 0);
INSERT INTO db_test.tb_customer (customer_id, customer_name, customer_tel, customer_point) VALUES (3, '王五', '00-88844666', 0);

create table tb_order
(
    order_id    int auto_increment
        primary key,
    customer_id int           not null,
    order_total decimal(6, 2) null,
    constraint tb_order_tb_customer_cumtomer_id_fk
        foreign key (customer_id) references tb_customer (customer_id)
);

INSERT INTO db_test.tb_order (order_id, customer_id, order_total) VALUES (1, 1, 120.00);
INSERT INTO db_test.tb_order (order_id, customer_id, order_total) VALUES (2, 1, 200.00);
INSERT INTO db_test.tb_order (order_id, customer_id, order_total) VALUES (3, 3, 650.00);
INSERT INTO db_test.tb_order (order_id, customer_id, order_total) VALUES (4, 2, 160.00);
INSERT INTO db_test.tb_order (order_id, customer_id, order_total) VALUES (5, 3, 450.00);
INSERT INTO db_test.tb_order (order_id, customer_id, order_total) VALUES (6, 2, 170.00);
INSERT INTO db_test.tb_order (order_id, customer_id, order_total) VALUES (7, 1, 110.00);
