package com.example.homeschoolplatform.entity;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String userType;
}
