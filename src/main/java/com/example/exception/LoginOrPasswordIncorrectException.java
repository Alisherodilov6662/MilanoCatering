package com.example.exception;

public class LoginOrPasswordIncorrectException extends RuntimeException{
    public LoginOrPasswordIncorrectException(String message) {
        super(message);
    }
}
