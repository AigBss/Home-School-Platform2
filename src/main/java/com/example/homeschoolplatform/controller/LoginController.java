package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.entity.User;
import com.example.homeschoolplatform.mapper.UserMapper;
import com.example.homeschoolplatform.service.UserService;
import com.example.homeschoolplatform.util.MD5Util;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    public Map<String, Object> login(@RequestBody Map<String, String> loginData, HttpServletResponse httpServletResponse) {
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
            response.put("user", user);
            // 创建并设置cookie
            Cookie userIdCookie = new Cookie("userId", String.valueOf(user.getId()));
            userIdCookie.setPath("/");
            userIdCookie.setDomain("localhost");
            Cookie userTypeCookie = new Cookie("userType", String.valueOf(user.getUserType()));

            // 设置cookie过期时间，例如1天（以秒为单位）
            userIdCookie.setMaxAge(24 * 60 * 60);
            userTypeCookie.setMaxAge(24 * 60 * 60);

            // 添加cookie到响应
            httpServletResponse.addCookie(userIdCookie);
            httpServletResponse.addCookie(userTypeCookie);
        } else {
            response.put("success", false);
            response.put("message", "Incorrect password.");
        }
        return response;
    }
}
