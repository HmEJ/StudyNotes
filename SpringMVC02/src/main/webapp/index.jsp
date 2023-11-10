<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<body>
<h2>Hello World!</h2>
    <form action="/emp/ts3" method="post">
        员工编号: <input type="text" name="empno"> <br>
        员工姓名: <input type="text" name="ename"> <br>
        员工工资: <input type="text" name="sal"> <br>
        是否毕业:
        <input type="radio" name="isgraduate" value="true"> 已经毕业
        <input type="radio" name="isgraduate" value="false"> 还未毕业 <br>
        部门名称: <input type="text" name="dept.dname"> <br>
        部门地址: <input type="text" name="dept.loc"> <br>
        最喜欢的城市: <br>
        <input type="checkbox" name="likes" value="beijing"> 北京 <br>
        <input type="checkbox" name="likes" value="shanghai"> 上海 <br>
        <input type="checkbox" name="likes" value="guangzhou"> 广州 <br>
        <input type="checkbox" name="likes" value="shenzhen"> 深圳 <br>
        <br>
        入职时间: <input type="date" name="hiredate">  <br>

        <input type="submit" value="登录">
    </form>
</body>
</html>
