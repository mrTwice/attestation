package ru.yampolskiy.taskclient.controller;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.yampolskiy.taskclient.models.task.Task;
import ru.yampolskiy.taskclient.service.FileGateway;
import ru.yampolskiy.taskclient.service.TaskService;
import ru.yampolskiy.taskclient.service.UserService;

import java.util.List;
@CommonsLog(topic = "TaskManagerController")
@Controller
public class TaskManagerController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private FileGateway fileGateway;

    @GetMapping("/tasks")
    public String getAllTasks(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Long currentUserId = userService.findUserByUserName(currentUsername).getId();
        List<Task> tasks = taskService.findAllUserTasks(currentUserId);
        model.addAttribute("tasks", tasks);

        fileGateway.writeToFile(  "ControllersLog.txt",
                this.getClass().getName() + ": "
                        + "user "
                        + currentUsername
                        + " get tasks");
        return "tasks";
    }

    @GetMapping("/tasks/{id}")
    public String getTaskById(@PathVariable Long id, Model model) {
        Task task = taskService.findTaskById(id);
        model.addAttribute("task", task);
        fileGateway.writeToFile(  "ControllersLog.txt",
                this.getClass().getName() + ": "
                        + "user "
                        + SecurityContextHolder.getContext().getAuthentication().getName()
                        + " get task with ID = "
                        + id
        );
        return "task";
    }

    @GetMapping("/tasks/new")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        fileGateway.writeToFile(  "ControllersLog.txt",
                this.getClass().getName() + ": "
                        + "user "
                        + SecurityContextHolder.getContext().getAuthentication().getName()
                        + " call create task function"
        );
        return "createTask";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute Task task) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Long currentUserId = userService.findUserByUserName(currentUsername).getId();
        task.setOwnerId(currentUserId);
        Task newTask = taskService.createNewTask(task);
        fileGateway.writeToFile(  "ControllersLog.txt",
                this.getClass().getName() + ": "
                        + "user "
                        + SecurityContextHolder.getContext().getAuthentication().getName()
                        + " create task with ID = "
                        + newTask.getId()
        );
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.findTaskById(id);
        model.addAttribute("task", task);
        fileGateway.writeToFile(  "ControllersLog.txt",
                this.getClass().getName() + ": "
                        + "user "
                        + SecurityContextHolder.getContext().getAuthentication().getName()
                        + " call edit task function"
        );
        return "editTask";
    }

    @PostMapping("/tasks/{id}/edit")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Task taskUpdated = taskService.updateTask(id, task);
        fileGateway.writeToFile(  "ControllersLog.txt",
                this.getClass().getName() + ": "
                        + "user "
                        + currentUsername
                        + " update task with ID = "
                        + taskUpdated.getId()
        );
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Long currentUserId = userService.findUserByUserName(currentUsername).getId();
        Task task = taskService.findTaskById(id);
        if (task != null && task.getOwnerId().equals(currentUserId)) {
            taskService.deleteTask(id);
        }
        fileGateway.writeToFile(  "ControllersLog.txt",
                this.getClass().getName() + ": "
                        + "user "
                        + SecurityContextHolder.getContext().getAuthentication().getName()
                        + " delete task with ID = "
                        + id
        );
        return "redirect:/tasks";
    }
}

