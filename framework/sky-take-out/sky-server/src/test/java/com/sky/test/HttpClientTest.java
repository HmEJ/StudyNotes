package com.sky.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @Classname HttpClientTest
 * @Description
 * @Date 2024/1/30 上午12:08
 * @Created by joneelmo
 */
@SpringBootTest
public class HttpClientTest {
    /**
     * 通过httpclient发送get方式请求
     */
    @Test
    public void testGet() throws IOException {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Http请求对象
        HttpGet httpGet = new HttpGet("http://localhost:8080/user/shop/status");
        //调用HttpClient的execute方法发送请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //解析响应结果
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("状态码：" + statusCode);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity);
        System.out.println("服务端返回的数据：" + s);
        //关闭对象
        response.close();
        httpClient.close();
    }

    /**
     * 通过httpclient发送post方式请求
     */
    @Test
    public void testPost() throws IOException {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Http请求对象
        HttpPost httpPost = new HttpPost("http://localhost:8080/admin/employee/login");

        /*构造请求数据*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "admin");
        jsonObject.put("password", "123456");
        StringEntity entity = new StringEntity(jsonObject.toJSONString());
        entity.setContentEncoding("utf-8");
        entity.setContentType("application/json");
        /*数据放到httpPost中*/
        httpPost.setEntity(entity);
        //调用HttpClient的execute方法发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("状态码：" + statusCode);
        HttpEntity e = response.getEntity();
        String s = EntityUtils.toString(e);
        System.out.println("服务端返回的数据：" + s);
        //关闭对象
        response.close();
        httpClient.close();
    }
}
