package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @Classname AutoFillAspect
 * @Description 自定义切面 实现公共字段自动填充逻辑
 * @Date 2024/1/29 下午3:00
 * @Created by joneelmo
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /**
     * 切入点 ： 对哪些类的哪些方法进行拦截
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut() {
    }

    /**
     * 前置通知 为公共字段赋值
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段的自动填充...");
        /*获取拦截到的方法的数据库操作类型*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType type = autoFill.value();  //拿到了操作类型
        /*获取方法的参数（实体对象）*/
        Object[] args = joinPoint.getArgs();
        /* 防止空指针异常 */
        if (args == null || args.length == 0) {
            return;
        }
        Object entity = args[0];
        /*准备赋值的数据*/
        LocalDateTime time = LocalDateTime.now();
        Long id = BaseContext.getCurrentId();
        /*根据不同的操作类型对属性赋值*/
        if (type == OperationType.INSERT) {
            /*为四个公共字段赋值*/
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setCreateTime.invoke(entity, time);
                setCreateUser.invoke(entity, id);
                setUpdateTime.invoke(entity, time);
                setUpdateUser.invoke(entity, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type == OperationType.UPDATE) {
            /*为两个字段赋值即可*/
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setUpdateTime.invoke(entity, time);
                setUpdateUser.invoke(entity, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
