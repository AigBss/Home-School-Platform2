package com.example.homeschoolplatform.service;

import com.example.homeschoolplatform.entity.RegisterRequest;
import com.example.homeschoolplatform.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    boolean checkPassword(String inputPassword, String storedPassword);
    boolean register(RegisterRequest registerRequest);
    boolean registerUser(User user);
    boolean updateUsername(Long userId, String newUsername);
    boolean updatePassword(Long userId, String newPassword);
    User getUserById(Long id);
    List<User> getAllUsers();
    boolean updateUserType(long id, int userType);

}
