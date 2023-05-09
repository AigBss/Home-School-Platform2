package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.entity.Notification;
import com.example.homeschoolplatform.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.createNotification(notification);
        return ResponseEntity.ok(createdNotification);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUserId(@PathVariable("userId") Integer userId) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<Notification>> getNotificationsByClassId(@PathVariable("classId") Integer classId) {
        List<Notification> notifications = notificationService.getNotificationsByClassId(classId);
        return ResponseEntity.ok(notifications);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable("id") Integer id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
