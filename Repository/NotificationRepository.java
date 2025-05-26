package com.example.GiangFroject.Repository;

import com.example.GiangFroject.models.Notification;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class NotificationRepository {
    private final Map<Long, Notification> notifications = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Notification save(Notification notification) {
        notification.setId(idGenerator.getAndIncrement());
        notifications.put(notification.getId(), notification);
        return notification;
    }

    public List<Notification> findAllByUserId(Long userId) {
        List<Notification> result = new ArrayList<>();
        for (Notification notification : notifications.values()) {
            if (notification.getUserId().equals(userId)) {
                result.add(notification);
            }
        }
        return result;
    }

    public List<Notification> findPendingByUserId(Long userId) {
        List<Notification> result = new ArrayList<>();
        for (Notification notification : notifications.values()) {
            if (notification.getUserId().equals(userId) && notification.isPending()) {
                result.add(notification);
            }
        }
        return result;
    }
}
