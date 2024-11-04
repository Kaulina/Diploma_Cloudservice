package ru.netology.diploma_cloudservice.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthToken {
    @JsonProperty("auth-token")
    private String authToken;
}