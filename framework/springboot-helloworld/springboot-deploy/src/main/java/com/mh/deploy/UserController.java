package com.mh.deploy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserController
 * @Description
 * @Date 2023/11/29 下午5:48
 * @Created by joneelmo
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @RequestMapping("/findAll")
    public String findAll(){
        return "success";
    }
}
