package com.mh.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String showExcep(){
        System.out.println("进入handler方阿飞。。。。");
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("500");
        return "/error/500";
    }
}
