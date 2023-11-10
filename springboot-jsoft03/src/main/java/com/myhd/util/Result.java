package com.myhd.util;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname Result
 * @Description TODO
 * @Date 2023/10/22 上午10:39
 * @Created by joneelmo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private Object object;
    private String msg;

    public static Result ok(String msg){
        return new Result(Code.OK_STATUS,true,msg);
    }

    public static Result failure(String msg){
        return new Result(Code.FAILURE_STATUS,false,msg);
    }

    public static Result ok(Integer code,Object object,String msg){
        return new Result(Code.OK_STATUS,object,msg);
    }

    public static Result failure(Integer code, Object object, String msg) {
        return new Result(Code.FAILURE_STATUS,object,msg);
    }
}
