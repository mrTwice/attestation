package ru.yampolskiy.taskmicroservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.yampolskiy.taskmicroservice.model.Task;
import ru.yampolskiy.taskmicroservice.service.TaskService;

import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    public void testGetUserTasks_ReturnsListOfUserTasks() {

        Long userId = 1L;
        List<Task> userTasks = Collections.singletonList(new Task());
        when(taskService.getAllUserTask(userId)).thenReturn(userTasks);

        ResponseEntity<List<Task>> response = taskController.getUserTasks(userId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(userTasks);
    }

    @Test
    public void testGetAllTasks_ReturnsListOfTasks() {

        List<Task> tasks = Collections.singletonList(new Task());
        when(taskService.getAllTasks()).thenReturn(tasks);

        ResponseEntity<List<Task>> response = taskController.getAllTasks();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(tasks);
    }

    @Test
    public void testGetTaskById_ExistingTask_ReturnsTask() {

        Long taskId = 1L;
        Task task = new Task();
        when(taskService.getTaskById(taskId)).thenReturn(task);

        ResponseEntity<Task> response = taskController.getTaskById(taskId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(task);
    }

    @Test
    public void testGetTaskById_NonExistingTask_ReturnsNotFound() {

        Long taskId = 1L;
        when(taskService.getTaskById(taskId)).thenReturn(null);

        ResponseEntity<Task> response = taskController.getTaskById(taskId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testCreateTask_ValidTask_CreatesTask() {

        Task task = new Task();
        when(taskService.createTask(task)).thenReturn(task);

        ResponseEntity<Task> response = taskController.createTask(task);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(task);
    }

    @Test
    public void testCreateTask_InvalidTask_ReturnsBadRequest() {

        Task task = new Task();
        doThrow(IllegalArgumentException.class).when(taskService).createTask(task);

        ResponseEntity<Task> response = taskController.createTask(task);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateTask_ExistingTask_UpdatesTask() {

        Long taskId = 1L;
        Task task = new Task();
        when(taskService.updateTask(taskId, task)).thenReturn(task);

        ResponseEntity<Task> response = taskController.updateTask(taskId, task);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(task);
    }

    @Test
    public void testUpdateTask_NonExistingTask_ReturnsNotFound() {

        Long taskId = 1L;
        Task task = new Task();
        doThrow(IllegalArgumentException.class).when(taskService).updateTask(taskId, task);

        ResponseEntity<Task> response = taskController.updateTask(taskId, task);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testDeleteTask_ExistingTask_DeletesTask() {

        Long taskId = 1L;

        ResponseEntity<Void> response = taskController.deleteTask(taskId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void testDeleteTask_NonExistingTask_ReturnsNotFound() {

        Long taskId = 1L;
        doThrow(IllegalArgumentException.class).when(taskService).deleteTask(taskId);

        ResponseEntity<Void> response = taskController.deleteTask(taskId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}