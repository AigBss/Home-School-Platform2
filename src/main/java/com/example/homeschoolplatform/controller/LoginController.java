package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.entity.User;
import com.example.homeschoolplatform.service.UserService;
import com.example.homeschoolplatform.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return "User not found.";
        }

        String encryptedPassword = MD5Util.encrypt(password);
        if (user.getPassword().equals(encryptedPassword)) {
            // 登录成功的处理逻辑，如创建会话
            return "Login successful.";
        } else {
            return "Incorrect password.";
        }
    }
}
