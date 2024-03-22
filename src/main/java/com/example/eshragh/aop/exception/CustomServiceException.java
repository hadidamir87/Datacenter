package com.example.eshragh.aop.exception;

import lombok.Data;
import lombok.Getter;

@Data
public class CustomServiceException extends Exception {
    private String errorCode;
    public CustomServiceException(){

    }
    public CustomServiceException(String errorCode) {
        this.errorCode = errorCode;
    }

    public CustomServiceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomServiceException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public CustomServiceException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public CustomServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

}
