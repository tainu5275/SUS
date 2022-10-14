package com.sus.exception;

public class BaseException extends RuntimeException{

    private int status;
    private String errorMessage;

    public BaseException(String errorMessage, int status){
        super(errorMessage);
        this.status =  status;
    }
}
