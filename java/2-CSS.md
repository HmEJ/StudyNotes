## 7-20 | 7-21
#7-20
### css 层叠样式表
- 作用: 
	1. 美化页面 ^9ab7b6
	2. 界面布局 css+div
- 表现形式：
	 1. 行内式 
		 直接在元素标签中写
		 `<div style="height: 200px;  width: 200px; background-color: #FF0000;"></div>`
	 2. 内嵌式
		 写在 `<head>` 标签中
		 `<style type="text/css"> ... </style>`
	 **3. 外链式**
		 写在 `<head>` 标签中
		 1. `<style type="text/css"> @import url("css文件路径") </style>`
		 2. `<link rel="stylesheet" href="css文件路径">` [^1]

[^1] : *rel = relationship  href= hypertext reference*

#### css选择器

^61de39

- ID选择器
- 类选择器
- 标签/元素 选择器
- 其他选择器 ^d7fe20
	- 空格(后代选择器)
		- `.a .b{ background-color:pink; }`
		- 表示从属关系，即对前一个元素的子元素起效。只有.b元素起效且.b是.a的子元素
	- 逗号
		- `.a,.b{ background-color:pink; }`
		- 表示或关系。多个元素间只需包含一个即可起效
	- 连写
		- `.a.b{ background-color:pink; }`
		- 表示与关系。当前元素在html中同时出现才会起效
	- > 符号
		- `.a > .b{background-color:pink;}`
		- 选择父元素中第一层的所有子元素
	- + 符号
		- `.a + .b{background-color:pink;}`
		- 选择与前一个元素同一层***紧邻***的后一个元素.如果不是***紧邻***则无法起效
	- ~符号
		- `.a ~ .b{background-color:pink;}`
		- 选择与前一个元素***同一层***后面所有的后一个元素
	- pseudo伪类
		- `.a:visited {color:blue}`
		- `.a div:nth-child(2){background-color:pink;}`



#7-21
#### css属性
- [Positon](https://developer.mozilla.org/zh-CN/docs/Web/CSS/position)
	- static: 该关键字指定元素使用正常的布局行为，此时 `top`, `right`, `bottom`, `left` 和 `z-index` 属性无效。
	 - relative: 该关键字下，元素先放置在未添加定位时的位置，再在不改变页面布局的前提下调整元素位置
	 - absolute: 元素会被移出正常文档流，并不为元素预留空间，通过指定元素相对于最近的非 static 定位祖先元素的偏移，来确定元素位置。绝对定位的元素可以设置外边距（margins），且不会与其他边距合并。
	 - fixed: 元素会被移出正常文档流，并不为元素预留空间，而是通过指定元素相对于屏幕视口（viewport）的位置来指定元素位置。元素的位置在屏幕滚动时不会改变。
	 - sticky: 元素根据正常文档流进行定位，然后相对它的_最近滚动祖先_（nearest scrolling ancestor）和 [containing block](https://developer.mozilla.org/zh-CN/docs/Web/CSS/Containing_block)（最近块级祖先 nearest block-level ancestor），包括 table-related 元素，基于 `top`、`right`、`bottom` 和 `left` 的值进行偏移。