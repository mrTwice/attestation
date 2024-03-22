package ru.yampolskiy.taskclient.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import ru.yampolskiy.taskclient.clients.TaskClientApi;
import ru.yampolskiy.taskclient.models.task.Task;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskClientApi taskClientApi;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testCreateNewTask() {
        Task task = new Task();
        when(taskClientApi.createTask(task)).thenReturn(ResponseEntity.ok(task));

        Task result = taskService.createNewTask(task);

        assertThat(result).isEqualTo(task);
    }

    @Test
    public void testDeleteTask() {
        Long taskId = 1L;
        when(taskClientApi.deleteTask(anyLong())).thenReturn(ResponseEntity.ok().build());

        taskService.deleteTask(taskId);

        verify(taskClientApi, times(1)).deleteTask(taskId);
    }

    @Test
    public void testUpdateTask() {
        Long taskId = 1L;
        Task task = new Task();
        when(taskClientApi.updateTask(taskId, task)).thenReturn(ResponseEntity.ok(task));

        Task result = taskService.updateTask(taskId, task);

        assertThat(result).isEqualTo(task);
    }

    @Test
    public void testFindTaskById() {
        Long taskId = 1L;
        Task task = new Task();
        when(taskClientApi.getTaskById(taskId)).thenReturn(ResponseEntity.ok(task));

        Task result = taskService.findTaskById(taskId);

        assertThat(result).isEqualTo(task);
    }

    @Test
    public void testFindAllUserTasks() {
        Long userId = 1L;
        List<Task> tasks = Collections.singletonList(new Task());
        when(taskClientApi.getUserTasks(userId)).thenReturn(ResponseEntity.ok(tasks));

        List<Task> result = taskService.findAllUserTasks(userId);

        assertThat(result).isEqualTo(tasks);
    }

    @Test
    public void testFindAllTask() {
        List<Task> tasks = Collections.singletonList(new Task());
        when(taskClientApi.getAllTasks()).thenReturn(ResponseEntity.ok(tasks));

        List<Task> result = taskService.findAllTask();

        assertThat(result).isEqualTo(tasks);
    }
}