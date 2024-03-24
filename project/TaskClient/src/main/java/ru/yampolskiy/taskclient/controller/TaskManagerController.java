package ru.yampolskiy.taskclient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.task.Task;
import ru.yampolskiy.taskclient.models.user.User;
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
    public String getAllTasks(Model model, Authentication authentication) throws JsonProcessingException {
        User currentUser = getCurrentUser(authentication);
        List<Task> tasks = taskService.findAllUserTasks(currentUser.getId()).getResponseData();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/tasks/{id}")
    public String getTaskById(@PathVariable Long id, Model model, HttpSession session) throws JsonProcessingException {
        CustomResponse<Task> customResponse = taskService.findTaskById(id);
        return handleResponse(customResponse, model, "task", session);
    }

    @GetMapping("/tasks/new")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "createTask";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute Task task, HttpSession session, Authentication authentication) throws JsonProcessingException {
        User currentUser = getCurrentUser(authentication);
        task.setOwnerId(currentUser.getId());
        CustomResponse<Task> customResponse = taskService.createNewTask(task);
        return handleResponse(customResponse, session, "redirect:/tasks");
    }

    @GetMapping("/tasks/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model, HttpSession session) throws JsonProcessingException {
        CustomResponse<Task> customResponse = taskService.findTaskById(id);
        return handleResponse(customResponse, model, "editTask", session);
    }

    @PostMapping("/tasks/{id}/edit")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task, HttpSession session) throws JsonProcessingException {
        CustomResponse<Task> customResponse = taskService.updateTask(id, task);
        return handleResponse(customResponse, session, "redirect:/tasks");
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id, HttpSession session) throws JsonProcessingException {
        CustomResponse<Task> customResponse = taskService.deleteTask(id);
        return handleResponse(customResponse, session, "redirect:/tasks");
    }

    private User getCurrentUser(Authentication authentication) throws JsonProcessingException {
        return userService.findUserByUserName(authentication.getName()).getResponseData();
    }

    private String handleResponse(CustomResponse<?> customResponse, Model model, String viewName, HttpSession session) {
        if (customResponse.getErrorCode() != 0) {
            session.setAttribute("exception", customResponse.getResponseError());
            return "redirect:/exception-data";
        } else {
            model.addAttribute("task", customResponse.getResponseData());
            return viewName;
        }
    }

    private String handleResponse(CustomResponse<?> customResponse, HttpSession session, String redirectUrl) {
        if (customResponse.getErrorCode() != 0) {
            session.setAttribute("exception", customResponse.getResponseError());
            return "redirect:/exception-data";
        } else {
            return redirectUrl;
        }
    }
}

