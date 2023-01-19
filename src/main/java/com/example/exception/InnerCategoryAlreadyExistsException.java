package com.example.exception;

public class InnerCategoryAlreadyExistsException extends RuntimeException{
    public InnerCategoryAlreadyExistsException(String message) {
        super(message);
    }
}
