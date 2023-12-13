
## ajax 异步通信

## 设置静态资源通行

### servlet default方式

将静态资源交给tomcat的默认servlet处理。这种方式效率较高

```html
<servlet-mapping>
    <servlet-name>default</servlet-name>
    <url-pattern>*.js</url-pattern>
</servlet-mapping>

<servlet-mapping>
<servlet-name>default</servlet-name>
<url-pattern>*.css</url-pattern>
</servlet-mapping>
```
### 通过springMVC提供的`<mvc:resources>`标签

放行静态资源

```html
<mvc:resources mapping="/js/*" location="js/"/>
<!-- 放行webapp下的js目录中的所有内容 -->
<!-- location属性的起始位置是web应用的根目录(webapp) -->
```


# 上传和下载

## 上传

### 添加依赖
```xml
<!-- 用于上传的依赖 -->
<dependency>
  <groupId>commons-fileupload</groupId>
  <artifactId>commons-fileupload</artifactId>
  <version>1.2.2</version>
</dependency>

<dependency>
  <groupId>commons-io</groupId>
  <artifactId>commons-io</artifactId>
  <version>2.4</version>
</dependency>

```
### 上传页面
```html
<body>
    <!-- enctype设置编码格式
        multipart/form-data是二进制的编码格式
     -->
    <form action="/emp/upload" method="post" enctype="multipart/form-data">
        用户名：<input type="text" name="uname"> <br>
        上传文件：<input type="file" name="upfile">
        <img src="http://localhost:8080/imgs/${url}" alt="upload result feedback">
        <br>
        <input type="submit" value="upload">
    </form>
</body>
```

### 编写Controller

核心代码：`upfile.transferTo(file)`

```java
@RequestMapping(value = "upload")
public String upload(String uname, HttpServletRequest request, MultipartFile upfile) throws IOException {
    /* 1.获得上传时文件名 */
    String filename = upfile.getOriginalFilename();
    System.out.println(filename);
    /* 2.获得文件发布的位置 （服务器地址）*/
    String url = request.getSession().getServletContext().getRealPath("/imgs/");
    System.out.println(url);
    System.out.println(url+filename);
    File file = new File(url+filename);
    upfile.transferTo(file);
    request.getSession().setAttribute("url", filename);
    return "redirect:/upload.jsp";
}
```
## 下载

```java
@RequestMapping("fdown")
public void downLoadFiles(String fname, HttpServletResponse response, HttpSession session) throws IOException {
    String url = session.getServletContext().getRealPath("/imgs/");
    File file = new File(url+fname);

    // 使用URL编码来处理文件名
    String encodedFileName = URLEncoder.encode(fname, "UTF-8").replaceAll("\\+", "%20");
    //设置响应类型  ==》 告诉浏览器当前是下载操作，我要下载东西
    response.setContentType("application/x-msdownload");
    //设置下载时文件的显示类型(即文件名称-后缀)   ex：txt为文本类型
    response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName);
    Files.copy(file.toPath(), response.getOutputStream());
}
```