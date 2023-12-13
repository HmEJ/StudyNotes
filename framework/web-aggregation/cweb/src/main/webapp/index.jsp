<%--
  Created by IntelliJ IDEA.
  User: 25499
  Date: 2023/10/8 008
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>系统主页</title>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.7.1/jquery.js"></script>
</head>
<body>
    <h1> SSM整合 </h1>

    <button name="query">Query</button>

    <br>

    <button name="empty">empty</button>

    <br>
    <br>
    员工号 ：<input type="text" name="empno"> <br>
    员工名 ：<input type="text" name="ename"> <br>
    职务 ：<input type="text" name="job"> <br>
    经理号 ：<input type="text" name="mgr"> <br>
    入职日 ：<input type="date" name="hiredate"> <br>
    薪水 ：<input type="text" name="sal"> <br>
    奖金 ：<input type="text" name="comm"> <br>
    部门号 ：<input type="text" name="deptno"> <br>

    <button name="add" >addEmp</button>
    <button name="delete" >removeEmp</button>
    <button name="change" >changeEmp</button>

    <br>
    <br>

    <input type="hidden" name="hid">

    <script>

        let empno;
        let ename;
        let job;
        let mgr;
        let hiredate;
        let sal;
        let comm;
        let deptno;

        function getValues() {
            empno = $("input[name='empno']").val()
            ename = $("input[name='ename']").val()
            job = $("input[name='job']").val()
            mgr = $("input[name='mgr']").val()
            hiredate = $("input[name='hiredate']").val()
            sal = $("input[name='sal']").val()
            comm = $("input[name='comm']").val()
            deptno = $("input[name='deptno']").val()
        }

        function emptyAll(){
            $("input[name='empno']").val("")
            $("input[name='ename']").val("")
            $("input[name='job']").val("")
            $("input[name='mgr']").val("")
            $("input[name='hiredate']").val("")
            $("input[name='sal']").val("")
            $("input[name='comm']").val("")
            $("input[name='deptno']").val("")
        }

        function getInfo(){
            $.ajax({
                url: "emp/getall",
                type: "get",
                success: function (list) {
                    console.log(list)

                    $("table").remove()

                    $("<table>"+
                           "<tr name='tr1'>"+
                                "<td>员工编号</td>"+
                                "<td>员工姓名</td>"+
                                "<td>岗位名称</td>"+
                                "<td>经理编号</td>"+
                                "<td>入职时间</td>"+
                                "<td>奖金绩效</td>"+
                                "<td>工资薪水</td>"+
                                "<td>部门编号</td>"+
                               " <td>部门名称</td>"+
                                "<td>部门地址</td>"+
                            "</tr>").insertAfter($("input[name='hid']"))


                    for (let i = 0; i < list.length; i++) {
                        $("<tr name='tr2'>"+
                            "<td>"+ list[i].empno +"</td>"+
                            "<td>"+ list[i].ename +"</td>"+
                            "<td>"+ list[i].job +"</td>"+
                            "<td>"+ list[i].mgr +"</td>"+
                            "<td>"+ list[i].hiredate +"</td>"+
                            "<td>"+ list[i].comm +"</td>"+
                            "<td>"+ list[i].sal +"</td>"+
                            "<td>"+ list[i].deptno +"</td>"+
                            "<td>"+ list[i].dept.dname +"</td>"+
                            "<td>"+ list[i].dept.loc +"</td>"+
                            "</tr>").insertAfter($("tr[name='tr1']"))
                    }

                    $(" </table> ").insertAfter($("tr[name='tr2']"))

                }
            })
        }

        $("button[name='query']").on("click",function () {
            getInfo();
        })

        $("button[name='empty']").on("click",function () {
            emptyAll();
        })

        $("button[name='add']").on("click",function () {
            getValues();
            $.ajax({
                url: "emp/addEmp",
                type: "put",
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify({
                    empno: empno,
                    ename: ename,
                    job: job,
                    mgr: mgr,
                    hiredate: hiredate,
                    sal: sal,
                    comm: comm,
                    deptno: deptno
                }),
                success: function(result){
                    if (result==="true"){
                        alert("成功！")
                    }else {
                        alert("失败！")
                    }
                    getInfo()
                }
            })
        })

        $("button[name='delete']").on("click",function () {
            getValues();
            $.ajax({
                url: "emp/removeEmp",
                type: "delete",
                data: {
                    empno: empno
                },
                success: function (result) {
                    if (result=="true"){
                        alert("成功")
                    }else {
                        alert("失败")
                    }
                    getInfo()
                }
            })
        })

        $("button[name='change']").on("click",function () {
            getValues();
            $.ajax({
                url: "emp/changeEmp",
                type: "post",
                contentType: "application/json",
                data: JSON.stringify({
                    empno: empno,
                    ename: ename,
                    job: job,
                    mgr: mgr,
                    hiredate: hiredate,
                    sal: sal,
                    comm: comm,
                    deptno: deptno
                }),
                success: function (result) {
                    if (result==="true"){
                        alert("成功！")
                    }else {
                        alert("失败！")
                    }
                    getInfo()
                }
            })
        })

    </script>
</body>
</html>
