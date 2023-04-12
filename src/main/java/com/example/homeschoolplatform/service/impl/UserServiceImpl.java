package com.example.homeschoolplatform.service.impl;

import com.example.homeschoolplatform.entity.RegisterRequest;
import com.example.homeschoolplatform.entity.User;
import com.example.homeschoolplatform.mapper.UserMapper;
import com.example.homeschoolplatform.service.UserService;
import com.example.homeschoolplatform.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public boolean checkPassword(String inputPassword, String storedPassword) {
        String md5Password = MD5Util.encrypt(inputPassword);
        return md5Password.equals(storedPassword);
    }

    @Override
    public boolean register(RegisterRequest registerRequest) {
        User existingUser = findByUsername(registerRequest.getUsername());
        if (existingUser != null) {
            return false;
        }
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(MD5Util.encrypt(registerRequest.getPassword()));
        newUser.setUserType(Integer.parseInt(registerRequest.getUserType()));
        userMapper.insertUser(newUser);
        return true;
    }

    @Override
    public boolean registerUser(User user) {
        int rowsAffected = userMapper.insertUser(user);
        return rowsAffected > 0;
    }
}
