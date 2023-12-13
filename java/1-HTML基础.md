## 7-19 | 7-20 | 
#7-19
**1. 网络编程三大基石**
	**a. url和uri**
		URL >> Uniform Resource locator 统一资源定位符 --网络间地址 
		https:// ip+port/项目名?值 
		URI >> Uniform Resource Identifier统一资源标识符 --本项目内地址
		img/aa.png
	**b. http协议(1.1版本)**
		三大特点：
		*单向性  -网络之间通信一定是由客户端向服务端发送请求，再有服务端给出响应
		长链接  -一旦客户端与服务端建立连接，可以无限次发送请求，直到客户端主动断开连接
		无状态 - 服务端不进行数据的存储* 
	**c. html**
		document type  &lt!DOCTYPE html&gt文档类型申明 有三种
		\<meta charset="utf-8"/\> 提供有关页面的元信息（meta-information），比如针对搜索引擎和更新频度的描述和关键词
		\<link rel="==icon==" href="" /\> 设置图标
		\<base /\>   规定页面上所有链接的默认 URL 和默认目标
		\<h1\> - \<h6\>  六级标题标签
**2. 超链接**
	`<a href="https://www.baidu.com/s?wd=12306" >百度</a>`
	s=search调用搜索模块  百度搜索引擎wd=word 关键词是12306 
	***本页面内跳转(锚点链接)：*** 
		 给一个a标签设定name属性。通过\<a href="#*name*"\> 可以实现本页面内跳转  ==可用于实现回到顶部或指定位置功能 ==
**3.超文本标签**
	图片:  img
	音视频: audio/video
	`<audio src="音频文件路径" controls autoplay loop preload="preload"></audio>`
	`<video src="" autoplay loop preload="preload" poster="url(图片的地址)"></video> `
**4.列表**
	Emmet语法：` ul[type="square"]{ni最喜欢的明星是:}>li{周星驰}*5`
**5.表格**
	==css选择器 空格选择器表示从属关系  逗号选择器表示或关系，只需要有一个就可以应用效果 连写表示与关系，即需要同时满足才能应用效果==
	单元格合并  列合并 colspan  行合并 rowspan
- *如何避免表格跨行合并的时候~~自动对齐~~ ？*
	- 答：通过[[2-CSS|css]] 强行控制单元格大小

---
#7-20
#### 表单
- form标签
	   action属性：提交位置/路径
	   method属性：提交方式( post | get )
			 1. get方式表单参数直接显示在地址栏 
				 1. 不安全 
				 2. 传递信息有**长度限制** 
				 3. 提交的数据有类型要求，一般是文本 
				 4. 是浏览器默认发送方式 
				 5. 有缓存功能-“[幂等](https://developer.mozilla.org/zh-CN/docs/Glossary/Idempotent)”
			 2. post方式不会将参数显示在地址栏 
				 1. 相对安全 
				 2. 传递信息**无长度限制** 
				 3. 提交的数据没有类型要求 
				 4. 不是浏览器默认发送方式
				 5. 无缓存功能
	   a标签作为表单的submit按钮: 
		`<a href="javascript:document.myform.submit()" >提交</a>`
		*(myform是表单的name属性)*
	   input关键属性：
		 *placeholder*属性：**提示信息**
		 *value*属性：**默认值，可被传参**
		 *maxlength*属性：**输入框可输入的最大长度**
		 *type*属性：**设置input类型**，例如*password | text | submit | radio | file | hidden | button | img* 等
	 **将文字或图片关联到radio单选框：**
		 *使用label标签包裹文字或图片，设置for属性为input的id值即可*
	 ==**H5新表单标签：**==
		1.颜色盘: `<input type="color"/>`
		2.时间控件: `<input type="date"/>`
		3.月份控件: `<input type="month"/>`
		4.星期控件: `<input type="week"/>`
		5.邮箱控件: `<input type="email">`
>**隐藏域传参：**
>`<input type="hidden" name="参数名" value="参数值"/>`
