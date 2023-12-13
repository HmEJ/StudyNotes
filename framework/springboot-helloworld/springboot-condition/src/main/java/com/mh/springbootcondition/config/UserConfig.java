package com.mh.springbootcondition.config;

import com.mh.springbootcondition.condition.ClassCondition;
import com.mh.springbootcondition.condition.ConditionOnClass;
import com.mh.springbootcondition.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname UserConfig
 * @Description
 * @Date 2023/11/27 下午6:24
 * @Created by joneelmo
 */
@Configuration
public class UserConfig {
    @Bean
//    @Conditional(ClassCondition.class)  //自定义的条件类
    @ConditionOnClass("redis.clients.jedis.Jedis")
    public User user(){
        return new User();
    }
}
