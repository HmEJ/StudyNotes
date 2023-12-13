<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 25499
  Date: 2023/10/12 012
  Time: 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>服务器文件展示页面</title>
</head>
<body>
    <h1>所有文件列表</h1>
    <c:forEach var="obj" items="${list}">
        <a href="/emp/fdown?fname=${obj}">${obj}</a> <br>
    </c:forEach>

</body>
</html>
