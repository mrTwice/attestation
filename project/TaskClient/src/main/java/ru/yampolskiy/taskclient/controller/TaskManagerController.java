package ru.yampolskiy.taskclient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.ExceptionData;
import ru.yampolskiy.taskclient.models.task.Task;
import ru.yampolskiy.taskclient.service.TaskService;
import ru.yampolskiy.taskclient.service.UserService;

import java.util.List;

@Controller
public class TaskManagerController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @GetMapping("/tasks")
    public String getAllTasks(Model model, HttpSession session) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Long currentUserId = userService.findUserByUserName(currentUsername).getId();
        CustomResponse<List<Task>> customResponse = taskService.findAllUserTasks(currentUserId);

        if(customResponse.getErrorCode() != 0) {
            return redirectIfExceptionExist(customResponse, session);
        }

        List<Task> tasks = customResponse.getResponseData();
        model.addAttribute("tasks", tasks);

        return "tasks";
    }

    @GetMapping("/tasks/{id}")
    public String getTaskById(@PathVariable Long id, Model model, HttpSession session) throws JsonProcessingException {
        return redirectIfTaskExist(id, model, session, "task");
    }

    @GetMapping("/tasks/new")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "createTask";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute Task task, HttpSession session) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Long currentUserId = userService.findUserByUserName(currentUsername).getId();
        task.setOwnerId(currentUserId);

        CustomResponse<Task> customResponse = taskService.createNewTask(task);
        if(customResponse.getErrorCode() != 0) {
            return redirectIfExceptionExist(customResponse, session);
        }

        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model, HttpSession session) throws JsonProcessingException {
        return redirectIfTaskExist(id, model, session, "editTask");
    }

    @PostMapping("/tasks/{id}/edit")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task, HttpSession session) throws JsonProcessingException {
        CustomResponse<Task> customResponse = taskService.updateTask(id, task);
        if(customResponse.getErrorCode() != 0) {
            return redirectIfExceptionExist(customResponse, session);
        }

        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id, HttpSession session) throws JsonProcessingException {
        CustomResponse<Task> customResponse = taskService.deleteTask(id);
        if(customResponse.getErrorCode() != 0) {
            return redirectIfExceptionExist(customResponse, session);
        }

        return "redirect:/tasks";
    }

    private String redirectIfTaskExist(Long id, Model model, HttpSession session, String redirectPath) throws JsonProcessingException {
        CustomResponse<Task> customResponse = taskService.findTaskById(id);
        if (customResponse.getErrorCode() != 0) {
            return redirectIfExceptionExist(customResponse, session);
        } else {
            Task task = customResponse.getResponseData();
            model.addAttribute("task", task);
            return redirectPath;
        }
    }

    private <T> String redirectIfExceptionExist(CustomResponse<T> customResponse, HttpSession session){
        ExceptionData exceptionData = customResponse.getResponseError();
        session.setAttribute("exception", exceptionData);
        return "redirect:/exception-data";
    }
}

