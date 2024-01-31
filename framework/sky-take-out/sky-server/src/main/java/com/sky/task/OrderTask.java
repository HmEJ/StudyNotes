package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Classname OrderTask
 * @Description
 * @Date 2024/1/30 下午8:46
 * @Created by joneelmo
 */
@Component
@Slf4j
public class OrderTask {

//    @Scheduled(cron = "0 * * * * ? ") //每分钟处理一次
    public void processTimeOutOrder(){
        log.info("定时处理超时订单...:{}", LocalDateTime.now());

    }

}
