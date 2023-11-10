<%--
  Created by IntelliJ IDEA.
  User: 25499
  Date: 2023/10/11 011
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.7.1/jquery.js"></script>
    <script>
        $(document).ready(function () {
            //发送异步
            $.ajax({
                url: "emp/getdepts",
                type: "post",
                success: function (result) {
                    JSON.stringify(result)
                    console.log(result)
                    for (let i = 0; i < result.length; i++) {
                        $("<p>"+result[i].dname+"</p>").appendTo("#div1")
                    }
                }
            })


            $("input[name='uname']").on("blur",function () {
                let uname = $(this).val();
                $.ajax({
                    url: "emp/getInfoByName",
                    type: "post",
                    data: {
                        uname: uname
                    },
                    success: function (result) {
                        JSON.stringify(result)
                        $("p").remove()
                        for (let i = 0; i < result.length; i++) {
                            $("<p>"+result[i].dname+"</p>").appendTo("#div1")
                        }
                    }
                })
            });


        });

    </script>

</head>
<body>
    <h1>窗体加载后，发送异步请求。获得后端数据，在前台页面展示</h1>
    <input type="text" name="uname">
    <div id="div1"></div>
</body>
</html>
