## 为什么要学阿贾克斯？
`Ajax (Asynchronous JavaScript and XML) `异步的 `JavaScript` 和 `XML`
**异步通讯，局部刷新**，不过度向数据库索求数据。减少IO操作，提高性能，降低数据库压力，优化用户体验。
Ajax：只刷新局部页面的技术


![](img/Snipaste_2023-09-04_10-25-12.png)

XMLHttpRequest常用属性:

![](img/Snipaste_2023-09-04_17-59-15.png)

常用方法：

![](img/Snipaste_2023-09-04_17-59-33.png)

原生ajax使用步骤：
1. 创建异步引擎对象
2. 设置一个请求 （请求方式，请求路径，参数）
3. 发送请求
4. 接收响应

以下是一个Ajax使用的示例代码：
```js
//原生ajax 以get方式为例
function postLogin(){
	//1.获得引擎对象
	let req = new XMLHttpRequest;
	//2.设置请求信息
	req.open("post","http://iwenwiki.com/api/blueberrypai/login.php");
	//3.发送请求
	let uname = document.getElementsByName("uname")[1].value;
	let pwd = document.getElementsByName("pwd")[1].value;
	let vali = document.getElementsByName("validate")[1].value;
	req.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	req.send("user_id="+uname+"&password="+pwd+"&verification_code="+vali); //如果不需要参数就传null
	//4.获得响应信息
	req.onreadystatechange = function(){
		if(req.readyState==4 && req.status==200){
			let resp = req.responseText;
			if(resp.success){
				console.info("登陆成功"+resp);
			}  else{
				console.info("登陆失败");
			}
		}else{
			console.info("失败...");
		}
	}
}
```
`JQuery`封装的`$.ajax`方式:
```js
$("document").ready(function(){
	$("#bt2").click(function(){
		$.ajax({
			"type":"post",
			"url":"http://iwenwiki.com/api/blueberrypai/getIndexInteresting.php",
			"contentType":"application/x-www-form-urlencoded",
			"success":function(respText){
				let arrs = respText.interesting;
				$("td[name='ajax2']").eq(0).text(arrs[1].title);
				$("td[name='ajax2']").eq(1).text(arrs[1].writer);
				$("td[name='ajax2']").eq(2).text(arrs[1].time);
				$("td[name='ajax2']").eq(3).text(arrs[1].content);
				$("td[name='ajax2']").eq(4).text(arrs[1].img);
			}
			
		});
	});
});
```
`JQuery`封装的`$.post / $.get`方式 
```js
$("document").ready(function(){
		$("#bt3").click(function(){			$.post("http://iwenwiki.com/api/blueberrypai/getIndexInteresting.php",function(respText){
			let arrs = respText.interesting;
			$("td[name='ajax3']").eq(0).text(arrs[2].title);
			$("td[name='ajax3']").eq(1).text(arrs[2].writer);
			$("td[name='ajax3']").eq(2).text(arrs[2].time);
			$("td[name='ajax3']").eq(3).text(arrs[2].content);
			$("td[name='ajax3']").eq(4).text(arrs[2].img);
		});  
	})
});
```
以上js代码对应的html代码：
```html
<body>
    <button id="bt1">原生ajax获取信息</button> 
    <button id="bt2">$.ajax获取信息</button> 
    <button id="bt3">$.post获取信息</button>
    <!--四行五列表格-->
    <table>
        <tr>
            <td>方式/标题</td>
            <td>title</td>
            <td>writer</td>
            <td>time</td>
            <td>content</td>
            <td>img</td>
        </tr>
        <tr>
            <td>原生ajax方式</td>
            <td name="ajax"></td>
            <td name="ajax"></td>
            <td name="ajax"></td>
            <td name="ajax"></td>
            <td name="ajax"></td>
        </tr>
        <tr>
            <td>$.ajax方式</td>
            <td name="ajax2"></td>
            <td name="ajax2"></td>
            <td name="ajax2"></td>
            <td name="ajax2"></td>
            <td name="ajax2"></td>
        </tr>
        <tr>
            <td>$.post方式</td>
            <td name="ajax3"></td>
            <td name="ajax3"></td>
            <td name="ajax3"></td>
            <td name="ajax3"></td>
            <td name="ajax3"></td>
        </tr>
    </table>
</body>
```