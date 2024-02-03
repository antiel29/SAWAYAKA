package com.api.dtos;

import jakarta.validation.constraints.Size;

public class UserRegisterDto {
    @Size(min=3,max=60, message = "Username must be between 3 and 50 characters")
    public String username;

    @Size(min=5,max=60,message = "Password must be between 5 and 50 characters")
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
