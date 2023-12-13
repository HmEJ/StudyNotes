# Servlet

创建一个简单的servlet程序：
```java
package com.mh.controller;  
  
import com.mh.entity.Emp;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import java.io.IOException;  
import java.io.PrintWriter;  
import java.util.ArrayList;  
  
/**  
 * @description servlet测试练习  
 * @author JoneElmo  
 * @date 2023-09-13 10:07  
 * @version 1.0  
 * @package com.mh.controller  
 * @class test  
 */
@SuppressWarnings("all")  
@WebServlet("/ts")  
public class test extends HttpServlet {  
  
    /*  
    * 处理客户端的get请求  
    * 一个servlet类只对应一个请求地址 == 编程规范。  
    * 正常来说，一个模块对应一个servlet类  
    * */    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        req.setCharacterEncoding("utf-8");  
        resp.setContentType("text/html;charset=utf-8");  /*设置编码格式*/  
  
        ArrayList<Emp> list = new ArrayList<>();  
        list.add(new Emp(12345, "张三", "销售"));  
        list.add(new Emp(12346, "李四", "开发"));  
        list.add(new Emp(12347, "王五", "开发"));  
        list.add(new Emp(12348, "赵六", "运维"));  
        list.add(new Emp(12349, "溜其", "测试"));  
  
        System.out.println("doget正在处理请求。。.");  
        String user = req.getParameter("user");  
        String password = req.getParameter("password");  
        PrintWriter writer = resp.getWriter();  
        if ("张三".equals(user) && "123".equals(password)){  
            writer.println("    <script>\n" +  
                    "      window.onload=function (){\n" +  
                    "        alert(\"登陆成功\")\n" +  
                    "      }\n" +  
                    "    </script>");  
            writer.println("<h1> 恭喜，登陆成功！ </h1>");  
            writer.println("    <table>\n" +  
                    "      <tr>\n" +  
                    "        <td>编号</td>\n" +  
                    "        <td>姓名</td>\n" +  
                    "        <td>职位</td>\n" +  
                    "      </tr>\n ");  
            for (int i = 0; i < list.size(); i++) {  
                writer.println("<tr>" +  
                        "<td>"+list.get(i).getEmpno()+"</td>" +  
                        "<td>"+list.get(i).getEname()+"</td>" +  
                        "<td>"+list.get(i).getJob()+"</td>" +  
                        "</tr>");  
            }  
            writer.println("</table>");  
        }else{  
            writer.println("    <script>\n" +  
                    "      window.onload=function (){\n" +  
                    "        alert(\"登陆失败\")\n" +  
                    "      }\n" +  
                    "    </script>");  
            writer.println("<h1> 3s后将回到首页 </h1>");  
            resp.setHeader("refresh", "3;url=index.jsp");  
        }  
    }  
    /*  
    * 处理客户端发出的post请求  
    * */    @Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        System.out.println("doPost方法正在处理请求。。。");  
    }  
    /*  
    * 做请求分发功能的。。请求在进入doGet或doPost之前会先进入service方法进行一个处理  
    * */    @Override  
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        System.out.println("service拦截到请求。。。正在处理中。。。");  
        super.service(req, resp);  
    }  
    @Override  
    public void destroy() {  
        System.out.println("servlet被销毁...");  
    }  
    @Override  
    public void init() throws ServletException {  
        System.out.println("servlet正在初始化。。。");  
    }  
    public test(){  
        System.out.println("servlet被创建...");  
    }  
}
```
Servlet 是一个伪单例模式，其底层原理是map集合。
### servlet生命周期：
创建servlet对象 -- 初始化( 调用`init`方法 ) -- 处理请求( 调用`service`方法 ) -- 销毁( 调用`destory`方法 )，最后由JVM垃圾回收机制进行垃圾回收

当服务器关闭时，该服务器中的所有servlet**全都会被销毁**。

参考: [Servlet生命周期详解](https://blog.csdn.net/super_DuoLa/article/details/122173346)

## 注册servlet两种方式
#### 在web.xml中配置servlet映射关系 (麻烦，不用)
#### 使用注解配置servlet

👉  `@WebServlet()`  👇

![](Snipaste_2023-09-13_14-31-30.png)

> 示例： `@WebServlet( urlPatterns = "/ts",  loadOnStartup = 1,  name = "myservlet1")`


## 获取请求行/请求头：
  A.请求方式：request.getMethod(); --- 用的比较多
  
  B.资源路径URL： request.getRequestURL();

  C.资源路径URI： request.getRequestURI();

  D.协议：request.getScheme();

  E.协议版本号：request.getProtocol();

  F.get方式的请求信息：request.getQueryString();

  G.当前项目部署的名称：request.getContextPath(); --- 用的比较多

### 关于ClassNotFound异常的思考
在 web 项目中使用某些依赖时，有时在运行时可能会出现该异常。这里提供一种解决办法：
将此依赖 `copy` 一份到 tomcat 的 lib 目录下。

### 请求转发和重定向
##### 请求转发：
`req.getRequestDispatcher("/dz10b.jsp").forward(req, resp);`
##### 重定向:
`resp.sendRedirect("/dz10b.jsp");`

请求转发到的页面既可以拿到参数 ( parameter ) ，也可以拿到attribute
重定向到的页面 ( dz10b.jsp ) 不能拿到参数。但是可以获取到 request 的 attribute。

# JSP
jsp本质上就是一个servlet，他们都存在于服务器内部。
## 内置对象
1. ***request 请求对象***  `作用域对象`  
2. **response 响应对象**  `输出对象`
3. *pageContext 页面上下文*  `作用域对象`
4. *application 应用程序对象*  `作用域对象`
5. **out 输出对象**   `输出对象`
6. config 配置对象
7. page 页面对象
8. exception 
9. *session 会话对象*  `作用域对象`

config 内置配置对象使用：
```java
//在web.xml中配置初始参数，可以在servlet中通过config对象获取
<servlet>  
    <servlet-name>TestServlet</servlet-name>  
    <servlet-class>com.mh.controller.TestServlet</servlet-class>  
    <init-param>  
        <param-name>strname</param-name>  
        <param-value>定制10班</param-value>  
    </init-param>  
</servlet>
//servlet
public class TestServlet extends HttpServlet {  
    @Override  
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        req.setCharacterEncoding("utf-8");  
        resp.setContentType("text/html;charset=utf-8");  
  
        ServletConfig config = this.getServletConfig();  
        resp.getWriter().println(config.getInitParameter("strname")+":"+config.getServletName());  
    }  
}
```

application ( ServletContext ) 内置对象使用：
```java
//在web.xml中配置全局参数
<context-param>  
    <param-name>schoolName</param-name>  
    <param-value>JSoft</param-value>  
</context-param>
//在servlet中使用
req.setCharacterEncoding("utf-8");  
resp.setContentType("text/html;charset=utf-8");  
  
ServletContext application = this.getServletContext();  
String schoolName = application.getInitParameter("schoolName");  
System.out.println(schoolName);

```
## EL表达式和JSTL（JSP标准标签库）
考虑如下代码：
```jsp
<%@ page import="com.mh.entity.Emp" %>  
<%@ page import="java.util.List" %>  
<%@ page contentType="text/html;charset=UTF-8" language="java" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  //引入JSTL
<html>  
<head>  
    <title>Title</title>  
</head>  
<body>  
    jsp语法：  
    <%=request.getAttribute("reqStr")%>  
  
    <%Emp e = (Emp)request.getAttribute("reqObj");%>  
    
    emp对象属性值：<%=e.getEname()%> -- <%=e.getEmpno()%> -- <%=e.getJob()%>  
  
    <table>  
        <tr>  
            <td>员工编号</td>  
            <td>员工姓名</td>  
            <td>工位</td>  
            <td>工资</td>  
        </tr>  
        <% List<Emp> reqlist = (List<Emp>) request.getAttribute("reqlist");%>  
        <%for(Emp ee:reqlist){%>  
        <tr>  
            <td><%=ee.getEmpno()%></td>  
            <td><%=ee.getEname()%></td>  
            <td><%=ee.getJob()%></td>  
            <td><%=ee.getSal()%></td>  
        </tr>  
        <%}%>  
    </table>  
  
    el表达式：  
    ${reqStr}  
  
    ${reqObj.empno} -- ${reqObj.ename} -- ${reqObj.job}  
  
  
    <table>  
        <c:forEach items="${reqlist}" var="emp">  
            <tr>  
                <td>${emp.empno}</td>  
                <td>${emp.ename}</td>  
                <td>${emp.job}</td>  
                <td>${emp.sal}</td>  
            </tr>  
        </c:forEach>  
    </table>  
    <%  
        double score = Math.random()*100+1;  
        pageContext.setAttribute("score", score);  
    %>  
    成绩是: ${score} <br>  
    <c:choose>  
        <c:when test="${score>=90}">优秀</c:when>  
        <c:when test="${score>=80 }">良好</c:when>  
        <c:when test="${score>=70 }">中等</c:when>  
        <c:when test="${score>=60}">及格</c:when>  
        <c:otherwise>不及格</c:otherwise>  
    </c:choose>  
  
</body>  
</html>
```

## 绝对路径和相对路径
- 绝对路径
带有协议名的地址。如: `https://www.baidu.com`
- 相对路径
不带协议名的地址。如: `user/some.do`
相对路径不能独立使用，必须有一个参考地址。
#### 表单
1. action 不加 `/` 表示相对地址。是相对于当前应用而言的地址
```jsp
<form action="some.do" method="post">
    姓名：<input type="text" name="name"><br/>
    年龄：<input type="text" name="age"><br/>
    <input type="submit" value="提交参数">
</form>
```
> 假设当前应用名为：`webapplication` ，则以上表单信息将被转发至 `http://localhost:8080/webapplication/some.do`

2. action 加 `/` 表示绝对地址。是对于**整个服务器**而言的
```jsp
<form action="/some.do" method="post">
    姓名：<input type="text" name="name"><br/>
    年龄：<input type="text" name="age"><br/>
    <input type="submit" value="提交参数">
</form>
```
>上述表单信息将被提交至 `http://localhost:8080/some.do`

#### base
在JSP页面中，为了防止因为地址产生的问题，通常会提前设置好页面的基准路径。我们可以动态获取该路径，主要用到以下几个方法：

1. `request.getSchema()  读[ski:m] `   可以返回当前页面使用的协议，http 或是 https;
2. `request.getServerName()`可以返回当前页面所在的服务器的名字;
3. `request.getServerPort()`可以返回当前页面所在的服务器使用的端口,就是80;
4. `request.getContextPath()`可以返回当前页面所在的应用的名字;

```jsp
<% 
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; 
%>
<base href="<%=basePath%>" />
```