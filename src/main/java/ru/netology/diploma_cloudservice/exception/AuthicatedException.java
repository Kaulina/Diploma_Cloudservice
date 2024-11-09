package ru.netology.diploma_cloudservice.exception;

public class AuthicatedException extends RuntimeException {

    public AuthicatedException() {
    }

    public AuthicatedException(String message) {
        super(message);
    }

    public AuthicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthicatedException(Throwable cause) {
        super(cause);
    }

    public AuthicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}