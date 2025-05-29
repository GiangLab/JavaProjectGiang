package com.example.GiangFroject.Scheduler;

import com.example.GiangFroject.Repository.TaskRepository;
import com.example.GiangFroject.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledTaskChecker {

    @Autowired
    private TaskRepository taskRepository;

    @Scheduled(fixedRate = 60000) // chạy mỗi 60s
    public void checkOverdueTasks() {
        List<Task> overdueTasks = taskRepository.findByTargetDateBeforeAndCompletedFalse(LocalDateTime.now());
        if (!overdueTasks.isEmpty()) {
            System.out.println("📢 Overdue tasks found: " + overdueTasks.size());
            overdueTasks.forEach(task ->
                    System.out.println("⚠️ Task '" + task.getTitle() + "' is overdue for user: " + task.getUserId())
            );
        }
    }
}
