package com.mh.service.impl;

import com.mh.service.Cal;
import org.springframework.stereotype.Component;

@Component
public class CalImpl implements Cal {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }

    @Override
    public int mul(int a, int b) {
        return a*b;
    }

    @Override
    public int div(int a, int b) {
        return a/b;
    }
}
