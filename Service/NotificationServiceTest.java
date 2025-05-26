package com.example.GiangFroject.Service;

import com.example.GiangFroject.models.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationServiceTest {
    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        notificationService = new NotificationService();
    }

    @Test
    public void testCreateNotification_Success() {
        Notification noti = new Notification(null, 1L, "You have a new task!");
        Notification result = notificationService.createNotification(noti);

        assertNotNull(result.getId());
        assertEquals("You have a new task!", result.getMessage());
    }

    @Test
    public void testCreateNotification_Invalid() {
        Notification noti = new Notification(null, null, null);
        assertThrows(IllegalArgumentException.class, () -> {
            notificationService.createNotification(noti);
        });
    }

    @Test
    public void testGetAllNotifications() {
        notificationService.createNotification(new Notification(null, 1L, "Task update"));
        List<Notification> result = notificationService.getAllNotifications(1L);

        assertEquals(1, result.size());
    }

    @Test
    public void testGetPendingNotifications() {
        Notification noti = new Notification(null, 1L, "Pending message");
        notificationService.createNotification(noti);

        List<Notification> result = notificationService.getPendingNotifications(1L);
        assertEquals(1, result.size());
    }
}
