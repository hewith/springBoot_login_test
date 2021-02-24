package com.hs.vueblog.common.exception;

import com.hs.vueblog.common.Result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class MyGlobalExceptionHandler {
    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail("执行了全局异常处理..");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.fail("执行了ArithmeticException异常处理..");
    }

    //自定义异常
    @ExceptionHandler(ComException.class)
    @ResponseBody //为了返回数据
    public Result error(ComException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.fail(e.getMsg());
    }
}
