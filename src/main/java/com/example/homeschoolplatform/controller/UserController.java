package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.entity.User;
import com.example.homeschoolplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}/username")
    public ResponseEntity<Object> updateUsername(@PathVariable long id, @RequestBody String newUsername) {
        boolean updated = userService.updateUsername(id, newUsername);
        if (updated) {
            return new ResponseEntity<>("Username updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}/password")
    public ResponseEntity<Object> updatePassword(@PathVariable long id, @RequestBody String newPassword) {
        boolean updated = userService.updatePassword(id, newPassword);
        if (updated) {
            return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
