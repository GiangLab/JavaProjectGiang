package com.example.GiangFroject.Service;

import com.example.GiangFroject.models.Notification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NotificationService {
    private final Map<Long, Notification> notifications = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Notification createNotification(Notification notification) {
        if (notification.getUserId() == null || notification.getMessage() == null) {
            throw new IllegalArgumentException("userId and message are required");
        }
        notification.setId(idGenerator.getAndIncrement());
        notifications.put(notification.getId(), notification);
        return notification;
    }

    public List<Notification> getAllNotifications(Long userId) {
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification : notifications.values()) {
            if (notification.getUserId().equals(userId)) {
                userNotifications.add(notification);
            }
        }
        return userNotifications;
    }

    public List<Notification> getPendingNotifications(Long userId) {
        List<Notification> pendingNotifications = new ArrayList<>();
        for (Notification notification : notifications.values()) {
            if (notification.getUserId().equals(userId) && notification.isPending()) {
                pendingNotifications.add(notification);
            }
        }
        return pendingNotifications;
    }
}