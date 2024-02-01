package com.mh.utils;

import cn.hutool.core.lang.UUID;
import jakarta.annotation.Resource;
import lombok.val;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @Classname SimpleRedisLock
 * @Description redis分布式锁的实现
 * @Date 2023/11/3 下午2:55
 * @Created by joneelmo
 */
public class SimpleRedisLock implements ILock {
    private String name;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public SimpleRedisLock(String name, StringRedisTemplate stringRedisTemplate) {
        this.name = name;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private static final String KEY_PREFIX = "lock:";
    private static final String ID_PREFIX = UUID.randomUUID().toString(true) + "-";
    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;

    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("unlock.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    @Override
    public boolean tryLock(long timeoutSec) {
        //获取线程表示
        val threadId = ID_PREFIX + Thread.currentThread().getId();
        //获取锁
        val success = stringRedisTemplate.opsForValue().setIfAbsent(KEY_PREFIX + name, threadId, timeoutSec, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(success);
    }

    @Override
    public void unlock() {
        //调用lua脚本
        stringRedisTemplate.execute(UNLOCK_SCRIPT, Collections.singletonList(KEY_PREFIX+name), ID_PREFIX + Thread.currentThread().getId())
    }
    //    @Override
//    public void unlock() {
//        //释放锁
//        //获取线程标识
//        val threadId = ID_PREFIX + Thread.currentThread().getId();
//        //获取锁的标识
//        val s = stringRedisTemplate.opsForValue().get(KEY_PREFIX + name);
//        //判断标识是否一致
//        if (threadId.equals(s)) {
//            stringRedisTemplate.delete(KEY_PREFIX + name);
//        }
//    }
}
