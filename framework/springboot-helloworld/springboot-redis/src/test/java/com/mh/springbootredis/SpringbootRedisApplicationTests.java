package com.mh.springbootredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.nio.charset.Charset;

@SpringBootTest
class SpringbootRedisApplicationTests {
	@Autowired
	private RedisTemplate redisTemplate;
	@Test
	public void testSet() {
		redisTemplate.boundValueOps("name").set("zhangsan");
	}
	@Test
	public void testGet(){
		System.out.println(redisTemplate.boundValueOps("name").get());
	}
}
