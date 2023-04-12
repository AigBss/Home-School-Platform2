package com.example.homeschoolplatform.service;

import com.example.homeschoolplatform.entity.RegisterRequest;
import com.example.homeschoolplatform.entity.User;

public interface UserService {
    User findByUsername(String username);
    boolean checkPassword(String inputPassword, String storedPassword);
    boolean register(RegisterRequest registerRequest);
    boolean registerUser(User user);
}
