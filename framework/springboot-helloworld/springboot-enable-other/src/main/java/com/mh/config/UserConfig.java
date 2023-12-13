package com.mh.config;

import com.mh.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname UserConfig
 * @Description
 * @Date 2023/11/29 下午2:56
 * @Created by joneelmo
 */
@Configuration
public class UserConfig {
    @Bean
    public User user(){
        return new User();
    }
}
