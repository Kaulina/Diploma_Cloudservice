package ru.netology.diploma_cloudservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponse(String authToken) {

    @JsonProperty(value = "auth-token")
    private String getAuthToken() {
        return authToken;
    }
}