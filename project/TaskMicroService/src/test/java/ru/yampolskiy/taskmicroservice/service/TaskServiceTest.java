package ru.yampolskiy.taskmicroservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.yampolskiy.taskmicroservice.exception.TaskIdNotNullException;
import ru.yampolskiy.taskmicroservice.exception.TaskNotFoundException;
import ru.yampolskiy.taskmicroservice.model.Task;
import ru.yampolskiy.taskmicroservice.model.TaskStatus;
import ru.yampolskiy.taskmicroservice.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllTasks() {
        List<Task> taskList = Collections.singletonList(new Task());
        when(taskRepository.findAll()).thenReturn(taskList);

        List<Task> result = taskService.getAllTasks();

        assertEquals(taskList, result);
    }

    @Test
    void testGetAllUserTask() {
        long userId = 1L;
        List<Task> taskList = Collections.singletonList(new Task());
        when(taskRepository.findAllByOwnerId(userId)).thenReturn(Optional.of(taskList));

        List<Task> result = taskService.getAllUserTask(userId);

        assertEquals(taskList, result);
    }

    @Test
    void testGetAllUserTask_EmptyList() {
        long userId = 1L;
        when(taskRepository.findAllByOwnerId(userId)).thenReturn(Optional.empty());

        List<Task> result = taskService.getAllUserTask(userId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetTaskById() {
        long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(taskId);

        assertEquals(task, result);
    }

    @Test
    void testGetTaskById_TaskNotFoundException() {
        long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(taskId));
    }

    @Test
    void testCreateTask() {
        Task task = new Task(null, "Test", "test", TaskStatus.OPEN, 1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Task task1 = new Task(1L, "Test", "test", TaskStatus.OPEN, 1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        when(taskRepository.save(task)).thenReturn(task1);

        Task result = taskService.createTask(task);

        // Проверяем, что созданная задача имеет правильные свойства
        assertNotNull(result.getId());
        assertEquals(TaskStatus.OPEN, result.getStatus());
        assertNotNull(result.getCreated());
        assertNotNull(result.getLastUpdate());
    }

    @Test
    void testCreateTask_TaskIdNotNullException() {
        Task task = new Task();
        task.setId(1L); // Устанавливаем ID, чтобы вызвать исключение

        assertThrows(TaskIdNotNullException.class, () -> taskService.createTask(task));
    }

    @Test
    void testUpdateTask() {
        long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);
        when(taskRepository.existsById(taskId)).thenReturn(true);
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.updateTask(taskId, task);

        assertEquals(task, result);
    }

    @Test
    void testUpdateTask_TaskNotFoundException() {
        long taskId = 1L;
        Task task = new Task();
        when(taskRepository.existsById(taskId)).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(taskId, task));
    }

    @Test
    void testDeleteTask() {
        long taskId = 1L;
        when(taskRepository.existsById(taskId)).thenReturn(true);

        assertDoesNotThrow(() -> taskService.deleteTask(taskId));
        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    void testDeleteTask_TaskNotFoundException() {
        long taskId = 1L;
        when(taskRepository.existsById(taskId)).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(taskId));
    }
}
