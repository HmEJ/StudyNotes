package com.mh.swagger.controller;

import com.mh.swagger.pojo.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname MyController
 * @Description
 * @Date 2023/11/29 下午9:04
 * @Created by joneelmo
 */
@RestController
public class MyController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @Operation(summary = "用户接口",description = "这是描述信息")
    @Parameter(name = "param2",description = "可选参数")
    @ApiResponse(description = "服务器错误",responseCode = "500")
    @PostMapping("/user")
    public User user(String param1){
        return new User();
    }

}
