package com.example.demo.exceptions;

public class NullValueException extends RuntimeException {
    public NullValueException(String message) {
        super(message);
    }
}