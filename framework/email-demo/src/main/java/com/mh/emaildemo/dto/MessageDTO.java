package com.mh.emaildemo.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname MessageDTO
 * @Description
 * @Date 2023/12/24 下午9:47
 * @Created by joneelmo
 */
@Component
@Data
public class MessageDTO {
    private String phone;
    private String code;
}
