package com.mh.swagger.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @Classname User
 * @Description
 * @Date 2023/11/29 下午9:46
 * @Created by joneelmo
 */
@Schema(name = "用户实体类")
public class User {
    public String userName;
    public String password;
}
