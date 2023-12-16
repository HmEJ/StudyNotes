package com.hmall.config;

import com.hmall.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

/**
 * @Classname DefaultFeignConfig
 * @Description
 * @Date 2023/12/13 下午3:46
 * @Created by joneelmo
 */
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor userInfoInterceptor(){
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                Long userId = UserContext.getUser();
                if (userId!=null){
                    template.header("user-info", userId.toString());
                }
            }
        };
    }
}
