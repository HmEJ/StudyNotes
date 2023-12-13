<%--
  Created by IntelliJ IDEA.
  User: 25499
  Date: 2023/10/8 008
  Time: 8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    ${info},登陆成功。

    <br>

    集合信息： <br>

    ${list}

    <br>

    ${map}

    <br>

    <form action="/emp/plupd" method="post">
        <table>
            <tr>
                <td>部门编号</td>
                <td>部门名称</td>
                <td>部门地址</td>
            </tr>
<%--            <c:forEach var="item" items="${list}" varStatus="status">--%>
<%--                <tr>--%>
<%--                    <td>--%>
<%--                        <input type="text" name="depts[${status.index}].deptno" value="${item.deptno}">--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <input type="text" name="depts[${status.index}].dname" value="${item.dname}">--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <input type="text" name="depts[${status.index}].loc" value="${item.loc}">--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>

            <c:forEach var="item" items="${map}">
                <tr>
                    <td>
                        <input type="text" name="deptMap[${item.key}].deptno" value="${item.value.deptno}">
                    </td>
                    <td>
                        <input type="text" name="deptMap[${item.key}].dname" value="${item.value.dname}">
                    </td>
                    <td>
                        <input type="text" name="deptMap[${item.key}].loc" value="${item.value.loc}">
                    </td>
                </tr>
            </c:forEach>

        </table>
        <input type="submit" value="批量修改">
    </form>



</body>
</html>
