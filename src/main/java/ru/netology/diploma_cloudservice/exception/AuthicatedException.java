package ru.netology.diploma_cloudservice.exception;

public class AuthicatedException extends RuntimeException {
    public AuthicatedException(String message) {
        super(message);
    }
}