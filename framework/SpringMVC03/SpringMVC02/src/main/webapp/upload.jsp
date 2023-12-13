<%--
  Created by IntelliJ IDEA.
  User: 25499
  Date: 2023/10/12 012
  Time: 8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>上传页面</title>
</head>
<body>
    <form action="/emp/upload" method="post" enctype="multipart/form-data">
        用户名：<input type="text" name="uname"> <br>
        上传文件：<input type="file" name="upfile">
        <img src="http://localhost:8080/imgs/${url}" alt="upload result feedback">
        <br>
        <input type="submit" value="upload">
        <a href="/emp/download">下载文件</a>
    </form>
</body>
</html>
