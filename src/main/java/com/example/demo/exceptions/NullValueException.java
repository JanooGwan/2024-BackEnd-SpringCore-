package com.example.demo.exceptions;

public class NullValueException extends RuntimeException {
    public NullValueException(String message) {
        super("에러 코드 : 400" + '\n' + message);
    }
}