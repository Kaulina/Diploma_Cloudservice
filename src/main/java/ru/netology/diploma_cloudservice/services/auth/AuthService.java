package ru.netology.diploma_cloudservice.services.auth;

import ru.netology.diploma_cloudservice.dto.LoginRequest;

public interface AuthService {
    String login(LoginRequest request);
}
