package com.mh.test;

import com.mh.springboottest.TestApplication;
import com.mh.springboottest.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname UserServiceTest
 * @Description UserService测试类
 * @Date 2023/11/27 下午3:20
 * @Created by joneelmo
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testAdd(){
        userService.add();
    }
}
