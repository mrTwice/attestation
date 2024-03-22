package ru.yampolskiy.taskmicroservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.yampolskiy.taskmicroservice.model.Task;
import ru.yampolskiy.taskmicroservice.repository.TaskRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testFindAll_ReturnsListOfTasks() {
        List<Task> tasks = Collections.singletonList(new Task());
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertThat(result).isEqualTo(tasks);
    }

    @Test
    public void testFindAllByOwnerId_ExistingTasks_ReturnsListOfTasks() {

        Long ownerId = 1L;
        List<Task> tasks = Collections.singletonList(new Task());
        when(taskRepository.findAllByOwnerId(ownerId)).thenReturn(Optional.of(tasks));

        List<Task> result = taskService.getAllUserTask(ownerId);

        assertThat(result).isEqualTo(tasks);
    }

    @Test
    public void testFindAllByOwnerId_NoTasks_ReturnsEmptyList() {

        Long ownerId = 1L;
        when(taskRepository.findAllByOwnerId(ownerId)).thenReturn(Optional.empty());


        List<Task> result = taskService.getAllUserTask(ownerId);


        assertThat(result).isEmpty();
    }

    @Test
    public void testFindById_ExistingTask_ReturnsTask() {

        Long taskId = 1L;
        Task task = new Task();
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(taskId);

        assertThat(result).isEqualTo(task);
    }

    @Test
    public void testFindById_NonExistingTask_ReturnsNull() {

        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());


        Task result = taskService.getTaskById(taskId);

        assertThat(result).isNull();
    }

    @Test
    public void testCreateTask_ValidTask_CreatesTask() {

        Task task = new Task();
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);

        assertThat(result).isEqualTo(task);
    }

    @Test
    public void testCreateTask_TaskWithId_ThrowsException() {

        Task task = new Task();
        task.setId(1L);


        assertThatThrownBy(() -> taskService.createTask(task))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ID должен быть пустым при создании задачи");
    }

    @Test
    public void testUpdateTask_ExistingTask_UpdatesTask() {

        Long taskId = 1L;
        Task task = new Task();
        when(taskRepository.existsById(taskId)).thenReturn(true);
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.updateTask(taskId, task);

        assertThat(result).isEqualTo(task);
    }

    @Test
    public void testUpdateTask_NonExistingTask_ThrowsException() {
        Long taskId = 1L;
        Task task = new Task();
        when(taskRepository.existsById(taskId)).thenReturn(false);

        assertThatThrownBy(() -> taskService.updateTask(taskId, task))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Задачи с ID " + taskId + " не существует");
    }

    @Test
    public void testDeleteTask_ExistingTask_DeletesTask() {
        Long taskId = 1L;
        when(taskRepository.existsById(taskId)).thenReturn(true);

        taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    public void testDeleteTask_NonExistingTask_ThrowsException() {

        Long taskId = 1L;
        when(taskRepository.existsById(taskId)).thenReturn(false);

        assertThatThrownBy(() -> taskService.deleteTask(taskId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Задачи с ID " + taskId + " не существует");
    }
}
