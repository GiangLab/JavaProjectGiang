package com.example.GiangFroject.Service;

import com.example.GiangFroject.models.Task;
import com.example.GiangFroject.Repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setTitle("Test Task");

        Mockito.when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.createTask(task);
        assertEquals("Test Task", result.getTitle());
    }
}
