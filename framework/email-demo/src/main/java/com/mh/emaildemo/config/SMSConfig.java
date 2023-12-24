package com.mh.emaildemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname SMSConfig
 * @Description
 * @Date 2023/12/24 下午10:11
 * @Created by joneelmo
 */
@Component
@ConfigurationProperties(prefix = "mh.message")
@Data
public class SMSConfig {
    private String accessKeyId;
    private String accessKeySecret;
}
