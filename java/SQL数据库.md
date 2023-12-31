**SQL语言**：用来操作*关系型数据库*
	DQL (data query language) 数据查询语言
	DML (data manipulation language) 数据操纵语言
	DDL (data definition language) 数据定义语言
	***TCL (transaction control language) 事务控制语言***

创建完数据表之后，经常需要查看表结构（表信息）。在 [MySQL](http://c.biancheng.net/mysql/) 中，可以使用 DESCRIBE 和 SHOW CREATE TABLE 命令来查看数据表的结构。
```sql
desc t_student;  -- 以表格形式查看表结构
show create table t_student;  -- 以sql语句形式查看表结构
```

### innoDB如何解决幻读？
InnoDB引擎采取了多版本并发控制（MVCC）来解决幻读问题。MVCC允许每个事务在不同的时间点看到数据库中的数据版本，而不会受到其他事务的影响。具体来说，InnoDB通过以下方式解决幻读问题：
1. 版本号：每个数据行都会有一个版本号，用来标识数据的修改次数。事务开始时会有一个全局的事务ID，每次修改数据时，数据行的版本号会更新为当前事务的ID。

2. 快照读：在InnoDB中，普通的SELECT查询被称为"快照读"，它会根据事务开始时的版本号来获取数据。这使得一个事务可以在执行期间保持一致的数据视图，即使其他事务对数据进行了修改。

3. 间隙锁：InnoDB在使用MVCC时，会对范围查询中不存在的数据行之间的"间隙"进行加锁，以防止其他事务在这些间隙中插入数据，从而避免幻读。

### B树和B+树
#### B树（B-Tree）：

B树是一种自平衡的多叉搜索树，其特点包括：

- 每个节点可以拥有多个子节点。这使得B树适用于存储在磁盘等外部存储设备上的大量数据。
- 节点的子节点按照一定的顺序排列，保证节点的数据是有序的。
- 每个节点可以包含多个关键字（数据），关键字之间按照一定的大小顺序排列。
- B树的每个节点都包含一定数量的关键字，节点的子节点数量为关键字数量加1，这使得B树保持了一个平衡的结构，从而提高了数据的检索效率。

#### B+树（B+ Tree）：

B+树是在B树基础上进行了优化和改进的数据结构，它在数据库系统中得到广泛应用，特点包括：

- B+树的内部节点只存储关键字信息，不存储数据，而数据都存储在叶子节点中。这使得叶子节点形成一个有序链表，方便范围查询。
- 叶子节点之间通过指针连接，形成一个有序的链表结构，使得范围查询非常高效。
- B+树的叶子节点都位于同一层，这种结构简化了遍历和查找操作。
- 由于只有叶子节点存储数据，B+树的节点可以存储更多的关键字，减少了树的层级，提高了查询效率。

总的来说，B树适用于范围查询和数据插入删除频繁的场景，而B+树更适合于范围查询和顺序遍历等操作，因此在大多数数据库系统中，如MySQL的InnoDB存储引擎，都使用了B+树作为索引结构。

## 索引的底层实现，为何选择B+树而不是红黑树？
索引在数据库系统中用于加速数据的检索，底层实现的选择取决于数据的特性和操作的需求。虽然红黑树也是一种自平衡的数据结构，但在数据库领域，B+树通常被选择用作索引的底层实现，而不是红黑树，原因如下：

1. **范围查询效率：** B+树在范围查询时效率更高。在B+树中，叶子节点形成一个有序链表，使得范围查询非常高效。而红黑树的叶子节点不一定具有有序性，因此范围查询可能需要更多的操作。

2. **磁盘IO优化：** 数据库中的数据通常存储在磁盘上，而不是内存中。B+树的每个节点可以容纳更多的关键字，这意味着在一个节点中能够存储更多的数据，从而减少了树的层级，降低了磁盘IO次数。而红黑树的节点数可能更多，导致更多的磁盘IO，影响了访问性能。

3. **更好的利用磁盘预读：** B+树的节点有序性使得磁盘预读（从磁盘读取一个节点时，往往会连带读取相邻的节点）更加有效，因为相关的数据通常紧凑地存储在一起。这在数据库系统中尤为重要，可以减少磁盘访问的随机性。

4. **适合磁盘写入：** B+树的结构在插入和删除操作时更适合磁盘的写入方式。B+树的叶子节点形成链表，有助于减少数据的分散写入，提高写入性能。

5. **更低的内存开销：** B+树的内部节点只存储关键字信息，而红黑树通常需要存储更多的附加信息，这会增加内存开销。

综合考虑，B+树更适合数据库索引的需求，特别是在大规模数据存储和高效查询的场景下。红黑树虽然在其他领域也有应用，但在数据库系统中，B+树的优势更加显著。
