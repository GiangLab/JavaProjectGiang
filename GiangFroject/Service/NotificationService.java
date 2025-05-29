package com.example.GiangFroject.Service;

import com.example.GiangFroject.models.Notification;
import com.example.GiangFroject.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(Notification notification) {
        if (notification.getUserId() == null || notification.getMessage() == null) {
            throw new IllegalArgumentException("userId and message are required");
        }
        notification.setPending(true);
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getPendingNotifications(Long userId) {
        return notificationRepository.findByUserIdAndPendingTrue(userId);
    }
}
