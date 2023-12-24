package com.mh.emaildemo.dto;

import lombok.Data;

/**
 * @Classname MailDTO
 * @Description
 * @Date 2023/12/24 下午8:33
 * @Created by joneelmo
 */
@Data
public class MailDTO {
    private String from;
    private String personal;
    private String to;
    private String subject;
    private String content;
}
