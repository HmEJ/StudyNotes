package com.mh.springbootmybatis;

import com.mh.springbootmybatis.domain.User;
import com.mh.springbootmybatis.mapper.UserMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SpringbootMybatisApplicationTests {

    @Autowired
    private UserMapper mapper;

    @Test
    public void selectTest() {
        List<User> list = mapper.findAll();
        System.out.println(list);
    }

}
