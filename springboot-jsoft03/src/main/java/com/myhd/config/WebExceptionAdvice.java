package com.myhd.config;

import com.myhd.util.Code;
import com.myhd.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Classname WebException
 * @Description TODO
 * @Date 2023/10/22 上午11:13
 * @Created by joneelmo
 */
@Slf4j
@RestControllerAdvice
public class WebExceptionAdvice {
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e){
        log.error(e.toString(),e);
        return Result.failure(Code.FAILURE_STATUS,false,"服务器异常");
    }
}
