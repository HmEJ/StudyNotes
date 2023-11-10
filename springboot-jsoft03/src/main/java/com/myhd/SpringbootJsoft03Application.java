package com.myhd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.myhd.mapper")
public class SpringbootJsoft03Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootJsoft03Application.class, args);
    }
}
