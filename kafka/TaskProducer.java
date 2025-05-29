package com.example.GiangFroject.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TaskProducer {

    private static final String TOPIC = "task_topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendTaskNotification(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
