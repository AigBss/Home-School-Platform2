package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.entity.ApiResponse;
import com.example.homeschoolplatform.entity.RegisterRequest;
import com.example.homeschoolplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        boolean result = userService.register(registerRequest);
        if (result) {
            return new ResponseEntity<>(new ApiResponse("Registration successful", HttpStatus.CREATED.value()), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new ApiResponse("Registration failed", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }
    }
}
