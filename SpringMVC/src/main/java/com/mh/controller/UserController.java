package com.mh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class UserController {

    @RequestMapping("reg.do")

    public ModelAndView show(String username,String password) {
        ModelAndView mv = new ModelAndView();

        if ("zhangsan".equals(username) && "123".equals(password)){
            mv.setViewName("success.jsp");
            mv.addObject("uname",username);
            return mv;
        }else {
            mv.setViewName("index.jsp");
            return mv;
        }

    }
}