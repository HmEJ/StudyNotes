package com.mh.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class CalAspect {

    @Before("execution(public int com.mh.service.impl.CalImpl.*(..))") /*前置通知*/
    public void getArgs(JoinPoint joinPoint){    /*切点*/
        String name = joinPoint.getSignature().getName(); /*获取方法名*/
        Object[] args = joinPoint.getArgs(); /*获取方法参数*/
        System.out.println("方法"+name+"的参数是"+ Arrays.toString(args));
    }

    @AfterReturning(value = "execution(public int com.mh.service.impl.CalImpl.*(..))",returning = "result")
    /* returning = "result" => 获取方法的返回值,将结果绑定到result参数上 */
    public void getResult(JoinPoint joinPoint,Object result){
        System.out.println("结果是：" + result );
    }

}

