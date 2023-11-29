package com.mh.springbootlistener.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Classname MyApplicationRunner
 * @Description
 * @Date 2023/11/29 下午5:19
 * @Created by joneelmo
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("applicationRunner...run");
        System.out.println(Arrays.asList(args.getSourceArgs()));
    }
}
