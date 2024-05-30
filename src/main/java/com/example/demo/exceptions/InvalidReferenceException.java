package com.example.demo.exceptions;

public class InvalidReferenceException extends RuntimeException {
    public InvalidReferenceException(String message) {
        super(message);
    }
}