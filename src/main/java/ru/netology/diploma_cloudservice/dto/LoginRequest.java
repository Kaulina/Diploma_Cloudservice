package ru.netology.diploma_cloudservice.dto;

public record LoginRequest(String login, String password) {
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}
