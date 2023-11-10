***Java DataBase Connectiviy ( Java 数据库连接 )***
## 使用JDBC连接数据库并进行增删改查操作
1. 注册JDBC驱动 ( JDK低版本需要此步骤，高版本可以跳过 )
	`Class.forName("com.mysql.cj.jdbc.Driver");`
2. 创建资源 `Connection`, `PreparedStatement`, `ResultSet`
	连接对象：`Connection conn = null;`
	执行对象：`PreparedStatement statement = null;`
	结果集：`ResultSet rs = null;`
3. 连接数据库
	`conn = DriverManager.getConnection(url, username, password);`
4. 创建操纵语句
	`String sql = "DELETE FROM db_student WHERE id=?";  //删除语句`  
	`statement = conn.prepareStatement(sql);`
	如果sql语句需要传参数, 使用`PreparedStatement`的`setString()`方法设置参数：
		`statement.setString(1, "4");`
5. 执行
	`statement.executeQuery();   //查询操作，返回值为结果集`
	`statement.executeUpdate();   //更新操作，返回值为影响的行数`

## DAO 模式
***DAO（Data Access Object）数据访问对象***
### 项目编写流程
1. 创建实体类
2. 提取工具类
3. 参考DAO模型
	DAO层是直接接触数据库的代码层
	DAO层规定了我们对数据库应该有哪些行为

***每层都写什么？***
![Alt text](img/Dao.png)
