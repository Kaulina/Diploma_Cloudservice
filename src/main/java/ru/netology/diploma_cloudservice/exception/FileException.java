package ru.netology.diploma_cloudservice.exception;

public class FileException extends RuntimeException {
    public FileException(String message) {
        super(message);
    }
}