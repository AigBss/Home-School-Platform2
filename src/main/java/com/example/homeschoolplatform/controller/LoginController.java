package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.entity.User;
import com.example.homeschoolplatform.service.UserService;
import com.example.homeschoolplatform.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        Map<String, Object> response = new HashMap<>();
        User user = userService.findByUsername(username);
        if (user == null) {
            response.put("success", false);
            response.put("message", "User not found.");
            return response;
        }

        String encryptedPassword = MD5Util.encrypt(password);
        if (user.getPassword().equals(encryptedPassword)) {
            // 登录成功的处理逻辑，如创建会话
            response.put("success", true);
            response.put("message", "Login successful.");
        } else {
            response.put("success", false);
            response.put("message", "Incorrect password.");
        }
        return response;
    }
}
