package com.itheima.mp.service;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.po.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Classname IUserServiceTest
 * @Description
 * @Date 2023/12/8 下午11:09
 * @Created by joneelmo
 */
@SpringBootTest
class IUserServiceTest {
    @Autowired
    private IUserService userService;
    private Page<User> p;

    @Test
    void testSaveUser(){
        User user = new User();
//        user.setId(5L);
        user.setUsername("Lilei");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo(UserInfo.of(24,"英文老师","female"));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userService.save(user);
    }

    @Test
    void testQuery(){
        List<User> users = userService.listByIds(List.of(1L, 2L, 3L));
        users.forEach(System.out::println);
    }

    @Test
    void testPageQuery(){
        int pageNO = 1, pageSize = 2 ;
        // 1. 准备分页条件
        Page<User> page = Page.of(pageNO, pageSize);
        // 1.1 排序条件
        page.addOrder(new OrderItem("balance", true));
        // 2. 分页查询
        Page<User> p = userService.page(page);
        // 3. 解析
        System.out.println(p.getTotal());
        System.out.println(p.getPages());
        List<User> records = p.getRecords();
        records.forEach(System.out::println);
    }
}