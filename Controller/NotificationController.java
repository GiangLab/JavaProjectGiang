package com.example.GiangFroject.Controller;

import com.example.GiangFroject.models.Notification;
import com.example.GiangFroject.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications(@RequestParam Long userId) {
        List<Notification> notifications = notificationService.getAllNotifications(userId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Notification>> getPendingNotifications(@RequestParam Long userId) {
        List<Notification> notifications = notificationService.getPendingNotifications(userId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
}