package com.example.homeschoolplatform.service;

import com.example.homeschoolplatform.entity.Notification;

import java.util.List;

public interface NotificationService {
    Notification createNotification(Notification notification);

    List<Notification> getNotificationsByUserId(Integer userId);

    List<Notification> getNotificationsByClassId(Integer classId);

    void deleteNotification(Integer id);
}
