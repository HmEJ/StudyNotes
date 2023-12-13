package com.mh.springbootlistener.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Classname MyCommandLineRunner
 * @Description
 * @Date 2023/11/29 下午5:18
 * @Created by joneelmo
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("commandlinerunner ... run");
        System.out.println(Arrays.asList(args));
    }
}
