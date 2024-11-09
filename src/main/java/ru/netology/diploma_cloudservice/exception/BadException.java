package ru.netology.diploma_cloudservice.exception;


public class BadException extends RuntimeException {
    public BadException() {
    }

    public BadException(String message) {
        super(message);
    }

    public BadException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadException(Throwable cause) {
        super(cause);
    }

    public BadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
