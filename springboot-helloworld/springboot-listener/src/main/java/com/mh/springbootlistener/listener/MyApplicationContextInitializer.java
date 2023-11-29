package com.mh.springbootlistener.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Classname MyApplicationContextInitializer
 * @Description
 * @Date 2023/11/29 下午5:11
 * @Created by joneelmo
 */
@Component
public class MyApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ApplicationContextInitializer.initialize");
    }
}
