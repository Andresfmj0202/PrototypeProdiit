package com.Prodiit.Prototype.Models.Responses;

import com.Prodiit.Prototype.Models.Entitys.UserEntity;

public class LoginResponse {
    private String token;
    private UserEntity user;

    public LoginResponse() {

    }
    public LoginResponse(String token, UserEntity user) {
        this.token = token;
        this.user = user;
    }

    // Getters y setters (puedes generarlos automáticamente en tu IDE)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
