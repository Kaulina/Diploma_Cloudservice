package ru.netology.diploma_cloudservice.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class ChangeFilenameRequest {
    private String filename;

    @JsonCreator
    public ChangeFilenameRequest(String filename) {
        this.filename = filename;
    }
}