package com.example.GiangFroject.Service;

import com.example.GiangFroject.models.Task;
import com.example.GiangFroject.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Autowired
    private NotificationService notificationService; // 👈 gọi async từ service khác

    @CacheEvict(value = "tasks", key = "#task.userId")
    public Task createTask(Task task) {
        if (task.getUserId() == null || task.getTitle() == null || task.getTargetDate() == null) {
            throw new IllegalArgumentException("userId, title, and targetDate are required");
        }
        task.setCreationDate(LocalDateTime.now());
        task.setCompleted(false);
        task.setDeleted(false);
        Task savedTask = taskRepository.save(task);

        // Gửi thông báo bất đồng bộ
        notificationService.sendAsyncNotification("New Task for user " + task.getUserId() + ": " + task.getTitle());

        return savedTask;
    }

    @Cacheable(value = "tasks", key = "#userId")
    public List<Task> getAllTasks(Long userId) {
        return taskRepository.findByUserIdAndDeletedFalse(userId);
    }

    public List<Task> getPendingTasks(Long userId) {
        return taskRepository.findByUserIdAndCompletedFalseAndDeletedFalse(userId);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setDeleted(true);
        taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
}
