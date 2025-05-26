package com.example.GiangFroject.Service;

import com.example.GiangFroject.models.Task; // ✅ Thêm dòng này

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Task createTask(Task task) {
        if (task.getUserId() == null || task.getTitle() == null || task.getTargetDate() == null) {
            throw new IllegalArgumentException("userId, title, and targetDate are required");
        }
        task.setId(idGenerator.getAndIncrement());
        tasks.put(task.getId(), task);
        return task;
    }

    public List<Task> getAllTasks(Long userId) {
        List<Task> userTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getUserId().equals(userId) && !task.isDeleted()) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }

    public List<Task> getPendingTasks(Long userId) {
        List<Task> pendingTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getUserId().equals(userId) && !task.isCompleted() && !task.isDeleted()) {
                pendingTasks.add(task);
            }
        }
        return pendingTasks;
    }

    public void deleteTask(Long id) {
        Task task = tasks.get(id);
        if (task == null) {
            throw new IllegalArgumentException("Task not found");
        }
        task.setDeleted(true);
    }

    public Task getTaskById(Long id) {
        return tasks.get(id);
    }
}