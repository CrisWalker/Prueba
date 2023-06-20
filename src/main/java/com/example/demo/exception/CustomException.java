package com.example.demo.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{
    private final String message;
    private final int status;

    public CustomException(String message, int status, Throwable e) {
        super(message, e);
        this.message = message;
        this.status = status;
    }

    public CustomException(String message, int status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
