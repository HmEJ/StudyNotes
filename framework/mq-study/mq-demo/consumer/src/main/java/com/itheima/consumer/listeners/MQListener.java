package com.itheima.consumer.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Classname MQListener
 * @Description
 * @Date 2024/1/1 下午6:42
 * @Created by joneelmo
 */
@Slf4j
@Component
public class MQListener {
/*    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String msg) {
        log.info("收到的消息是：" + msg);
        throw new RuntimeException("故意的异常");
    }*/

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue1(String msg) throws InterruptedException {
        System.err.println("no.1收到的消息是：" + msg);
        Thread.sleep(20);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.out.println("no.2收到的消息是：" + msg);
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void fanoutListener1(String msg) throws InterruptedException {
        System.out.println("no.1收到的消息是：" + msg);

    }

    @RabbitListener(queues = "fanout.queue2")
    public void fanoutListener2(String msg) throws InterruptedException {
        Thread.sleep(20);
        System.out.println("no.2收到的消息是：" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1", durable = "true"),
            exchange = @Exchange(name = "hmall.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void directqueue1(String msg) {
        System.out.println("no.1收到的消息是：" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2", durable = "true"),
            exchange = @Exchange(name = "hmall.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void directqueue2(String msg) {
        System.out.println("no.2收到的消息是：" + msg);
    }

    @RabbitListener(queues = "topic.queue1")
    public void topicqueue1(String msg) {
        System.out.println("no.1收到的消息是：" + msg);
    }

    @RabbitListener(queues = "topic.queue2")
    public void topicqueue2(String msg) {
        System.out.println("no.2收到的消息是：" + msg);
    }

    @RabbitListener(queues = "object.queue")
    public void objectQueue(Map<String, Object> msg) {
        System.out.print("接受到的对象是：");
        System.out.println(msg);
    }

    @RabbitListener(queues = "dlx.queue")
    public void dlxQueue(Map<String, Object> msg) {
        log.info("死信 监听到的消息是：" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "delay.queue",durable = "true"),
            exchange = @Exchange(name = "delay.direct",delayed = "true"),
            key = "hi"
    ))
    public void listenDelayMessage(String msg){
        log.info("监听到的delay消息是: "+msg);
    }
}
