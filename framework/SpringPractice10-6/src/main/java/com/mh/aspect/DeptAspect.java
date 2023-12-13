package com.mh.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DeptAspect {

    @Before("execution(* com.mh.service.impl.DeptServiceImpl.queryAll())")
    public void check(){
        System.out.println("权限校验...");
    }
    @After("execution(* com.mh.service.impl.DeptServiceImpl.queryAll())")
    public void log(){
        System.out.println("日志打印...");
    }
}
