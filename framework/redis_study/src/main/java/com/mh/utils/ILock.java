package com.mh.utils;

/**
 * @Classname ILock
 * @Description
 * @Date 2023/11/3 下午2:54
 * @Created by joneelmo
 */
public interface ILock {

    boolean tryLock(long timeoutSec);

    void unlock();
}
