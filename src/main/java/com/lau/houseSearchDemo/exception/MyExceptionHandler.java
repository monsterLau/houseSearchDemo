package com.lau.houseSearchDemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handlerMyException(MyException ex){
        Map<String,Object> result = new HashMap<>();
        result.put("message", ex.getMessage());
        return result;
    }
}
