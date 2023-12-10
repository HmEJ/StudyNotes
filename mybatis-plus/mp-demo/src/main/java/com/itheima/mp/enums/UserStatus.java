package com.itheima.mp.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Classname UserStatus
 * @Description
 * @Date 2023/12/10 下午2:43
 * @Created by joneelmo
 */
@Getter
public enum UserStatus {
    NORMAL(1,"正常"),
    FROZEN(2,"冻结"),
    ;
    @EnumValue
    @JsonValue
    private final int value;
    private final String desc;

    UserStatus(int value,String desc){
        this.value = value;
        this.desc = desc;
    }
}
