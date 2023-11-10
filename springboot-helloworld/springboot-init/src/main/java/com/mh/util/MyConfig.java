package com.mh.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Resource
    private MyInteceptor myInteceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myInteceptor).addPathPatterns("/**").excludePathPatterns("/getAll");
    }
}
