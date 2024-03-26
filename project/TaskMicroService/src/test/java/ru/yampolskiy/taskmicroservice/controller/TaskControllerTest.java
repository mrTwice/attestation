package ru.yampolskiy.taskmicroservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.yampolskiy.taskmicroservice.model.CustomResponse;
import ru.yampolskiy.taskmicroservice.model.Task;
import ru.yampolskiy.taskmicroservice.service.TaskService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUserTasks() {
        long userId = 1L;
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());

        when(taskService.getAllUserTask(userId)).thenReturn(tasks);

        ResponseEntity<CustomResponse<List<Task>>> responseEntity = taskController.getUserTasks(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(0, responseEntity.getBody().getErrorCode());
        assertEquals(tasks, responseEntity.getBody().getResponseData());
    }

    @Test
    void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());

        when(taskService.getAllTasks()).thenReturn(tasks);

        ResponseEntity<CustomResponse<List<Task>>> responseEntity = taskController.getAllTasks();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(0, responseEntity.getBody().getErrorCode());
        assertEquals(tasks, responseEntity.getBody().getResponseData());
    }

    @Test
    void testGetTaskById() {
        long taskId = 1L;
        Task task = new Task();

        when(taskService.getTaskById(taskId)).thenReturn(task);

        ResponseEntity<CustomResponse<Task>> responseEntity = taskController.getTaskById(taskId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(0, responseEntity.getBody().getErrorCode());
        assertEquals(task, responseEntity.getBody().getResponseData());
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        Task createdTask = new Task();

        when(taskService.createTask(task)).thenReturn(createdTask);

        ResponseEntity<CustomResponse<Task>> responseEntity = taskController.createTask(task);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(0, responseEntity.getBody().getErrorCode());
        assertEquals(createdTask, responseEntity.getBody().getResponseData());
    }

    @Test
    void testUpdateTask() {
        long taskId = 1L;
        Task task = new Task();
        Task updatedTask = new Task();

        when(taskService.updateTask(taskId, task)).thenReturn(updatedTask);

        ResponseEntity<CustomResponse<Task>> responseEntity = taskController.updateTask(taskId, task);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(0, responseEntity.getBody().getErrorCode());
        assertEquals(updatedTask, responseEntity.getBody().getResponseData());
    }

    @Test
    void testDeleteTask() {
        long taskId = 1L;

        ResponseEntity<CustomResponse<Task>> responseEntity = taskController.deleteTask(taskId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(0, responseEntity.getBody().getErrorCode());
        assertEquals(null, responseEntity.getBody().getResponseData());
    }
}
