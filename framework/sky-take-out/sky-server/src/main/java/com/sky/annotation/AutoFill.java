package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname AutoFill
 * @Description 自定义注解，用来标识某个方法需要进行公共字段自动填充处理
 * @Date 2024/1/29 下午2:57
 * @Created by joneelmo
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    /*指定操作类型：update || insert*/
    OperationType value();
}
