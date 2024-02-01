package com.mh.redis_study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
public class RedisStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisStudyApplication.class, args);
    }

}
