package com.example.homeschoolplatform.service.impl;

import com.example.homeschoolplatform.entity.RegisterRequest;
import com.example.homeschoolplatform.entity.User;
import com.example.homeschoolplatform.mapper.UserMapper;
import com.example.homeschoolplatform.service.UserService;
import com.example.homeschoolplatform.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {//类 "UserServiceImpl" 必须声明为抽象，或为实现 "UserService" 中的抽象方法 "getUserById(Long)"
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
        User newUser = new User();//'com.example.homeschoolplatform.entity.User' 中的 'User(java.lang.Integer, java.lang.String, java.lang.String)' 无法应用于 '()'
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
    @Override
    public boolean updateUsername(Long userId, String newUsername) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return false;
        }
        user.setUsername(newUsername);
        int rowsAffected = userMapper.updateByPrimaryKeySelective(user);
        return rowsAffected > 0;
    }

    @Override
    public boolean updatePassword(Long userId, String newPassword) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return false;
        }
        user.setPassword(MD5Util.encrypt(newPassword));
        int rowsAffected = userMapper.updateByPrimaryKeySelective(user);
        return rowsAffected > 0;
    }
    @Override
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    };

    @Override
    public boolean updateUserType(long id, int userType) {
        return userMapper.updateUserType(id, userType) > 0;
    }

}
