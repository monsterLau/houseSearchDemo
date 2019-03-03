package com.lau.houseSearchDemo.exception;

/**
 * 继承异常类
 */
public class MyException extends Exception{

    private static final long serialVersionUID = 3841605392526067532L;

    public MyException(String message) {
        super(message);
    }
}
