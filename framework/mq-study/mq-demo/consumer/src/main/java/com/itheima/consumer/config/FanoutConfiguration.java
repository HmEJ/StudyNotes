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
@Configuration
public class FanoutConfiguration {
    @Bean
    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange("hmall.fanout2");
        return ExchangeBuilder.fanoutExchange("hmall.fanout2").build();
    }

    @Bean
    public Queue fanoutQueue() {
//        return new Queue("fanout.queue3"); //默认就是持久化队列
        return QueueBuilder.durable("fanout.queue3").build(); //持久化队列(数据持久化)
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());   //直接传入方法  加了Bean注解的方法都会被动态代理
    }

//    @Bean
//    public Binding binding1(Queue fanoutQueue, FanoutExchange fanoutExchange) {   //参数注入
//        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
//    }
}
