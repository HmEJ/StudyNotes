package com.mh.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Classname EnableUser
 * @Description
 * @Date 2023/11/29 下午3:26
 * @Created by joneelmo
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(UserConfig.class)
public @interface EnableUser {
}
