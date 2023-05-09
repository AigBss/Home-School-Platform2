package com.example.homeschoolplatform.service.impl;

import com.example.homeschoolplatform.entity.Notification;
import com.example.homeschoolplatform.mapper.NotificationMapper;
import com.example.homeschoolplatform.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public Notification createNotification(Notification notification) {
        notificationMapper.insert(notification);
        return notification;
    }

    @Override
    public List<Notification> getNotificationsByUserId(Integer userId) {
        return notificationMapper.getNotificationsByUserId(userId);
    }

    @Override
    public List<Notification> getNotificationsByClassId(Integer classId) {
        return notificationMapper.getNotificationsByClassId(classId);
    }

    @Override
    public void deleteNotification(Integer id) {
        notificationMapper.deleteByPrimaryKey(id.longValue());
    }
}
