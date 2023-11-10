#### 1. 请统计出订单表中每一位顾客的积分, 积分为消费总金额的2倍, 如会使用视图, 请使用视图更新顾客表的积分字段, 不会则手动更新
```sql
UPDATE tb_customer
SET customer_point = 
(SELECT SUM(order_total) FROM tb_order WHERE tb_order.customer_id = tb_customer.customer_id) * 2;
```
### 2. having子句和where子句的区别
```
where后跟分组前条件  
having后跟分组后条件

```
### 3. 顾客表里有顾客姓名, id, 电话和积分,请使用where子查询统计订单总表(有顾客id和他们的消费金额)中单笔消费金额大于600的顾客
```sql

select * from tb_customer 
where customer_id = (select customer_id from tb_order where order_total > 600)

```
### 4. 请回答为什么一线互联网公司严禁使用存储过程
```
数据库作为数据存储单位，就应该专注于数据存储，而不要把一些业务逻辑参杂到其中。并且由于数据库没有行业规范制约，导致语法成了方言。Oracle的存储过程语法无法在Mysql中使用。所以存储过程移植性很差。
数据库没有合适的日志系统，一旦存储过程出现bug，多数情况下程序员只能肉眼debug，非常难受。
综上，存储过程能不用就不用。
但实际上这个问题仁者见仁智者见智。也有人认为存储过程用的好可以减少数据库读写，提高性能。这里所说的禁止使用存储过程是对于阿里这种高并发的应用场景来说，系统不容出错。存储过程这种维护困难，上手门槛高，以及扩展性差的缺点，确实不适用于这种场景。
```
### 5. 请说出至少4点sql优化规则
```
1. 拥有良好的排版，提高可读性
2. 不要在sql中使用select * ，使用完整的列名来替代
3. 如果字符串长度几乎相等，使用char定长字符串类型
4. 表的命名最好遵循“业务名称_表的作用” 如alipay_task 

```
### 6. 请根据一张顾客表查询出顾客的姓名, id, 电话, 积分级别(查询自设字段),但是要根据顾客的积分赋予不同的积分级别, 大于500白银会员, 大于800黄金会员, 大于1000白金会员
```sql
select customer_id,customer_name,customer_tel,
case 
	when customer_point>1000 then '白金会员'
	when customer_point>800 then '黄金会员'
	when customer_point>500 then '白银会员'
	else '普通会员'
	end as '积分级别'
from tb_customer
```
### 7. 请回答sql语句中all关键字和any关键字的区别
```
all，any都是行子查询中用到的关键字。
子查询通常有多行数据，在where条件后进行判断。

如果是大于all，就相当于大于子查询的最大值
如果是小于all，就相当于小于子查询的最小值。

如果是大于any，就相当于大于子查询的最小值
如果是小于any，就相当于是小于子查询的最大值。

总的来说，all就是所有，any就是任一。


```
### 8.(选做题) 顾客表里有顾客姓名, id, 电话和积分,请使用where子查询统计订单总表(有顾客id和他们的消费金额)中宗消费金额大于600的顾客
```sql
select c.customer_id,customer_name,customer_tel,customer_point from tb_customer c
join (select o.customer_id,SUM(order_total) sum from tb_order o group by o.customer_id) total 
on c.customer_id = total.customer_id
where total.sum > 600
```




















































```
select *
from tb_customer
join (select t.customer_id, sum(order_total) as total
from tb_order t group by customer_id) as tb_total
using (customer_id)
where tb_total.total > 1000
```