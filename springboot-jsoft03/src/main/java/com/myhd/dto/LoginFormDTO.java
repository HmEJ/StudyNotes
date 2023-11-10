package com.myhd.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Classname LoginFormDTO
 * @Description TODO
 * @Date 2023/10/22 下午5:00
 * @Created by joneelmo
 */
@Data
public class LoginFormDTO {
    private String phone;

    private String email;

    private String userName;

    private String password;

    private String code;
}
