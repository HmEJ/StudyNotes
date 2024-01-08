package com.itheima.publisher;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Classname SpringAmqpTest
 * @Description
 * @Date 2024/1/1 下午6:37
 * @Created by joneelmo
 */
@SpringBootTest
@Slf4j
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testSendMessage2Queue() {
        String queueName = "simple.queue";
        String content = "hello little rabbit 2222222 !";
        rabbitTemplate.convertAndSend(queueName, content);
    }

    @Test
    void testWorkQueue() throws InterruptedException {
        String queueName = "work.queue";
        for (int i = 0; i < 50; i++) {
            String msg = "hello worker! no." + i;
            rabbitTemplate.convertAndSend(queueName, msg);
            Thread.sleep(20);
        }
    }

    @Test
    void testFanout() {
        String exchangeName = "hmall.fanout";
        String msg = "hello everyone!";
        rabbitTemplate.convertAndSend(exchangeName, null, msg);
    }

    @Test
    void testDirect() {
        String exchangeName = "hmall.direct";
        String msg = "yellow吧噶压路！！!";
        rabbitTemplate.convertAndSend(exchangeName, "yellow", msg);
    }

    @Test
    void testTopic() {
        String exchangeName = "hmall.topic";
        String msg = "天气通知。。。";
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", msg);
    }

    @Test
    void objectTest() {
        Map<String, Object> msg = new HashMap<>();
        msg.put("name", "jack");
        msg.put("age", 21);
        rabbitTemplate.convertAndSend("object.queue", msg);
    }

    @Test
    void testConfirmCallback() throws InterruptedException {
        //1.创建cd
        CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
        //2.添加ConfirmCallback
        cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("消息回调失败", ex);
            }

            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                log.debug("受到confirm callback回执");
                if (result.isAck()) { //消息发送成功
                    log.debug("消息发送成功，收到ack");
                } else { //消息发送失败
                    log.error("消息发送失败，收到nack，原因：{}", result.getReason());
                }
            }
        });
        rabbitTemplate.convertAndSend("hmall.directt", "reddd", "hello", cd);
        Thread.sleep(2000);
    }

    @Test
    void testSendTTLMessage() {
//        rabbitTemplate.convertAndSend("simple.direct", "hi", "hello", new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                message.getMessageProperties().setExpiration("5000");
//                return message;
//            }
//        });
//        log.info("消息发送成功");
        Message msg = MessageBuilder.withBody("hello".getBytes())
                .setExpiration("1000*10") //设置过期时间为10s
                .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT) //设置发送模式为非持久化消息
                .build();
        rabbitTemplate.convertAndSend("simple.direct","hi",msg);
        log.info("消息发送成功");
    }

    @Test
    void testDelayMessage(){
        rabbitTemplate.convertAndSend("delay.direct", "hi", "hello,delay!!", message -> {
            message.getMessageProperties().setDelay(10000);
            return message;
        });
    }


}
