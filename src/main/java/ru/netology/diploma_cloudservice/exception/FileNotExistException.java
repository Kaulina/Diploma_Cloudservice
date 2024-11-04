package ru.netology.diploma_cloudservice.exception;

public class FileNotExistException extends RuntimeException {
    public FileNotExistException(String message) {
        super(message);
    }
}