package ru.yampolskiy.taskclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yampolskiy.taskclient.clients.TaskClientApi;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.task.Task;
import ru.yampolskiy.taskclient.models.task.TaskStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskClientApi taskClientApi;

    @Test
    void testCreateNewTask_Success() throws JsonProcessingException {
        Task newTask = new Task(1L, "Test", "Test Description", TaskStatus.OPEN, 1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        CustomResponse<Task> expectedResponse = new CustomResponse<>(0, newTask);

        when(taskClientApi.createTask(newTask)).thenReturn(expectedResponse);

        CustomResponse<Task> actualResponse = taskService.createNewTask(newTask);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testDeleteTask_Success() throws JsonProcessingException {
        Long taskId = 1L;
        CustomResponse<Task> expectedResponse = new CustomResponse<>(0, null);

        when(taskClientApi.deleteTask(taskId)).thenReturn(expectedResponse);

        CustomResponse<Task> actualResponse = taskService.deleteTask(taskId);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testUpdateTask_Success() throws JsonProcessingException {
        Long taskId = 1L;
        Task updatedTask = new Task(taskId, "Updated Test", "Updated Test Description", TaskStatus.IN_PROGRESS, 1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        CustomResponse<Task> expectedResponse = new CustomResponse<>(0, updatedTask);

        when(taskClientApi.updateTask(taskId, updatedTask)).thenReturn(expectedResponse);

        CustomResponse<Task> actualResponse = taskService.updateTask(taskId, updatedTask);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testFindTaskById_Success() throws JsonProcessingException {
        Long taskId = 1L;
        Task expectedTask = new Task(taskId, "Test", "Test Description", TaskStatus.OPEN, 1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        CustomResponse<Task> expectedResponse = new CustomResponse<>(0, expectedTask);

        when(taskClientApi.getTaskById(taskId)).thenReturn(expectedResponse);

        CustomResponse<Task> actualResponse = taskService.findTaskById(taskId);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testFindAllUserTasks_Success() throws JsonProcessingException {
        Long userId = 1L;
        List<Task> expectedTasks = new ArrayList<>();
        Task task1 = new Task(1L, "Task 1", "Task 1 Description", TaskStatus.OPEN, userId, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Task task2 = new Task(2L, "Task 2", "Task 2 Description", TaskStatus.IN_PROGRESS, userId, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        expectedTasks.add(task1);
        expectedTasks.add(task2);
        CustomResponse<List<Task>> expectedResponse = new CustomResponse<>(0, expectedTasks);

        when(taskClientApi.getUserTasks(userId)).thenReturn(expectedResponse);

        CustomResponse<List<Task>> actualResponse = taskService.findAllUserTasks(userId);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testFindAllTask_Success() throws JsonProcessingException {
        List<Task> expectedTasks = new ArrayList<>();
        Task task1 = new Task(1L, "Task 1", "Task 1 Description", TaskStatus.OPEN, 1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Task task2 = new Task(2L, "Task 2", "Task 2 Description", TaskStatus.IN_PROGRESS, 1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        expectedTasks.add(task1);
        expectedTasks.add(task2);
        CustomResponse<List<Task>> expectedResponse = new CustomResponse<>(0, expectedTasks);

        when(taskClientApi.getAllTasks()).thenReturn(expectedResponse);

        CustomResponse<List<Task>> actualResponse = taskService.findAllTask();
        assertEquals(expectedResponse, actualResponse);
    }
}

