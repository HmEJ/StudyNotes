package com.mh.ssm;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMyBatis {
    @Test
    public void testMyBatis(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSessionFactoryBean sqlSessionFactoryBean = (SqlSessionFactoryBean) context.getBean("sqlSessionFactoryBean");
        System.out.println(sqlSessionFactoryBean);
    }

}
