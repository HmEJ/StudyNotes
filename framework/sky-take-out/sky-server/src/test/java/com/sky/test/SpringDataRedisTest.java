package com.sky.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Classname SpringDataRedisTest
 * @Description
 * @Date 2024/1/29 下午10:58
 * @Created by joneelmo
 */
//@SpringBootTest
public class SpringDataRedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate(){
//        System.out.println(redisTemplate);
        System.out.println(redisTemplate.opsForValue().get("name"));
    }
}
