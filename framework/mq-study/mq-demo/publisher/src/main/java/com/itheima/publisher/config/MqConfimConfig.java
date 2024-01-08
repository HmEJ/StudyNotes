package com.itheima.publisher.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname MqConfimConfig
 * @Description
 * @Date 2024/1/3 下午4:20
 * @Created by joneelmo
 */
@Slf4j
@Configuration
public class MqConfimConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        //配置回调
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                log.debug("收到消息的return callback. exchange:{},routingkey:{},message:{},replycode:{},replytext:{}",
                        returnedMessage.getExchange(), returnedMessage.getRoutingKey(),
                        returnedMessage.getMessage(), returnedMessage.getReplyCode(),
                        returnedMessage.getReplyText());
            }
        });
    }
}
