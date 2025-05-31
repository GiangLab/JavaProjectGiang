package com.example.GiangFroject.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @KafkaListener(topics = "task_topic", groupId = "group_id")
    public void listen(String message) {
        System.out.println("📩 Received Notification: " + message);

        // TODO: xử lý logic lưu notification nếu cần
    }
}
