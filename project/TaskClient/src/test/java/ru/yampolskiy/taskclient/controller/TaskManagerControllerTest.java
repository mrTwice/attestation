package ru.yampolskiy.taskclient.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.task.Task;
import ru.yampolskiy.taskclient.models.user.Role;
import ru.yampolskiy.taskclient.models.user.User;
import ru.yampolskiy.taskclient.service.TaskService;
import ru.yampolskiy.taskclient.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class TaskManagerControllerTest {

    @Mock
    private TaskService taskService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TaskManagerController taskManagerController;

    @Test
    void testGetAllTasks() throws JsonProcessingException {
        Model model = Mockito.mock(Model.class);
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        CustomResponse<List<Task>> customResponse = new CustomResponse<>(0, tasks);

        User user = new User(1L, "username", "updateduser@example.com", "newpassword", true, Collections.singleton(Role.USER));
        CustomResponse<User> customResponse1 = new CustomResponse<User>(0, user, null);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");

        Mockito.when(taskService.findAllUserTasks(anyLong())).thenReturn(customResponse);
        Mockito.when(userService.findUserByUserName("username")).thenReturn(customResponse1);
        String result = taskManagerController.getAllTasks(model, authentication);

        assertEquals("tasks", result);
        Mockito.verify(model).addAttribute("tasks", tasks);
    }

    @Test
    void testGetTaskById() throws JsonProcessingException {
        Task task = new Task();
        CustomResponse<Task> customResponse = new CustomResponse<>(0, task);
        Mockito.when(taskService.findTaskById(anyLong())).thenReturn(customResponse);

        Model model = Mockito.mock(Model.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        String result = taskManagerController.getTaskById(1L, model, session);

        assertEquals("task", result);
        Mockito.verify(model).addAttribute("task", task);
    }

    @Test
    void testCreateTaskForm() {
        Model model = Mockito.mock(Model.class);
        String result = taskManagerController.createTaskForm(model);
        assertEquals("createTask", result);
    }

    @Test
    void testCreateTask() throws JsonProcessingException {
        Task task = new Task();
        CustomResponse<Task> customResponse = new CustomResponse<>(0, task);
        Mockito.when(taskService.createNewTask(Mockito.any(Task.class))).thenReturn(customResponse);

        HttpSession session = Mockito.mock(HttpSession.class);
        User user = new User(1L, "username", "updateduser@example.com", "newpassword", true, Collections.singleton(Role.USER));
        CustomResponse<User> customResponse1 = new CustomResponse<User>(0, user, null);
        Mockito.when(userService.findUserByUserName("username")).thenReturn(customResponse1);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        String result = taskManagerController.createTask(task, session, authentication);

        assertEquals("redirect:/tasks", result);
    }

    @Test
    void testEditTaskForm() throws JsonProcessingException {
        Task task = new Task();
        CustomResponse<Task> customResponse = new CustomResponse<>(0, task);
        Mockito.when(taskService.findTaskById(anyLong())).thenReturn(customResponse);

        Model model = Mockito.mock(Model.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        String result = taskManagerController.editTaskForm(1L, model, session);

        assertEquals("editTask", result);
        Mockito.verify(model).addAttribute("task", task);
    }

    @Test
    void testUpdateTask() throws JsonProcessingException {
        Task task = new Task();
        CustomResponse<Task> customResponse = new CustomResponse<>(0, task);
        Mockito.when(taskService.updateTask(anyLong(), Mockito.any(Task.class))).thenReturn(customResponse);

        HttpSession session = Mockito.mock(HttpSession.class);
        String result = taskManagerController.updateTask(1L, task, session);

        assertEquals("redirect:/tasks", result);
    }

    @Test
    void testDeleteTask() throws JsonProcessingException {
        Task task = new Task();
        CustomResponse<Task> customResponse = new CustomResponse<>(0, task);
        Mockito.when(taskService.deleteTask(anyLong())).thenReturn(customResponse);

        HttpSession session = Mockito.mock(HttpSession.class);
        String result = taskManagerController.deleteTask(1L, session);

        assertEquals("redirect:/tasks", result);
    }
}
