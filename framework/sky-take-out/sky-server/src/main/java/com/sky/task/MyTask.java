package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Classname MyTask
 * @Description
 * @Date 2024/1/30 下午8:38
 * @Created by joneelmo
 */
@Component
@Slf4j
public class MyTask {
//    @Scheduled(cron = "0/5 * * * * ?")
    public void executeTask(){
        log.info("time:{}", new Date());
    }
}
