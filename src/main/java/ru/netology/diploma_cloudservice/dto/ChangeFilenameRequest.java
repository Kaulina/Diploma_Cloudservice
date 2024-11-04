package ru.netology.diploma_cloudservice.dto;

public record ChangeFilenameRequest(String filename) {
    public String getFilename() {
        return filename;
    }
}