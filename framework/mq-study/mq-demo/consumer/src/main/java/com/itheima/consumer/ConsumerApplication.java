package com.itheima.consumer;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    public MessageConverter jacksonMessageConvertor() {
        Jackson2JsonMessageConverter jjmc = new Jackson2JsonMessageConverter();
        //配置自动创建消息id，可以识别不同消息，也可以判断是否重复消息
        jjmc.setCreateMessageIds(true);
        return jjmc;
    }
}
