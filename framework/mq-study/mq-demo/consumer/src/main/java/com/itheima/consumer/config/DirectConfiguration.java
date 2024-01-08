package com.itheima.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname FanoutConfiguration
 * @Description
 * @Date 2024/1/2 下午2:28
 * @Created by joneelmo
 */
//@Configuration
public class DirectConfiguration {
    @Bean
    public DirectExchange directExchange() {
//        return new FanoutExchange("hmall.fanout2");
        return ExchangeBuilder.fanoutExchange("hmall.direct").build();
    }

    @Bean
    public Queue directQueue() {
//        return new Queue("fanout.queue3"); //默认就是持久化队列
        return QueueBuilder.durable("direct.queue1").build(); //持久化队列(数据持久化)
    }

    @Bean
    public Binding directQueueBinding(Queue directQueue1,DirectExchange directExchange){
        return BindingBuilder.bind(directQueue1).to(directExchange).with("red");
    }
}
