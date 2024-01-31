package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }
    /**
     * 处理sql异常
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException e){
        /*Duplicate entry 'ZHANGSAN' for key 'employee.idx_username'*/
        String msg = e.getMessage();
        String result = null;
        if (msg.contains("Duplicate entry")){
            String[] split = msg.split(" ");
            String userName = split[2];
            result = userName+ MessageConstant.ALREADY_EXISTS;
        }else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
        return Result.error(result);
    }
}
