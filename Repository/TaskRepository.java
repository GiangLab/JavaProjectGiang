package com.example.GiangFroject.Repository;

import com.example.GiangFroject.models.Task;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {
    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Task save(Task task) {
        task.setId(idGenerator.getAndIncrement());
        tasks.put(task.getId(), task);
        return task;
    }

    public List<Task> findAllByUserId(Long userId) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getUserId().equals(userId) && !task.isDeleted()) {
                result.add(task);
            }
        }
        return result;
    }

    public List<Task> findPendingByUserId(Long userId) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getUserId().equals(userId) && !task.isDeleted() && !task.isCompleted()) {
                result.add(task);
            }
        }
        return result;
    }

    public Task findById(Long id) {
        return tasks.get(id);
    }

    public void softDelete(Long id) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setDeleted(true);
        }
    }
}
