package com.example.GiangFroject.Service;

import com.example.GiangFroject.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskService = new TaskService();
    }

    @Test
    public void testCreateTask_Success() {
        Task task = new Task(null, 1L, "Title", "Desc", LocalDateTime.now().plusDays(1));
        Task result = taskService.createTask(task);

        assertNotNull(result.getId());
        assertEquals("Title", result.getTitle());
    }

    @Test
    public void testCreateTask_Invalid() {
        Task task = new Task(null, null, null, "desc", null);
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.createTask(task);
        });
    }

    @Test
    public void testGetAllTasks() {
        Task task1 = new Task(null, 1L, "Task 1", "Desc 1", LocalDateTime.now());
        taskService.createTask(task1);
        List<Task> tasks = taskService.getAllTasks(1L);

        assertFalse(tasks.isEmpty());
    }

    @Test
    public void testDeleteTask_SoftDelete() {
        Task task = taskService.createTask(new Task(null, 1L, "Title", "Desc", LocalDateTime.now()));
        taskService.deleteTask(task.getId());

        List<Task> tasks = taskService.getAllTasks(1L);
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void testGetPendingTasks() {
        Task task = new Task(null, 1L, "Title", "Desc", LocalDateTime.now().plusDays(2));
        taskService.createTask(task);

        List<Task> pendingTasks = taskService.getPendingTasks(1L);
        assertEquals(1, pendingTasks.size());
    }
}
