package com.example.exception;

/**
 * Author: Alisher Odilov
 * Date: 19.01.2023
 */
public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
