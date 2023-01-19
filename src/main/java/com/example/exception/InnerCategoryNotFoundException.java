package com.example.exception;

public class InnerCategoryNotFoundException extends RuntimeException{
    public InnerCategoryNotFoundException(String message) {
        super(message);
    }
}
