package ru.yampolskiy.taskmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yampolskiy.taskmicroservice.model.CustomResponse;
import ru.yampolskiy.taskmicroservice.model.Task;
import ru.yampolskiy.taskmicroservice.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/user/{id}")
    public ResponseEntity<CustomResponse<List<Task>>> getUserTasks (@PathVariable Long id){
        List<Task> userTasks = taskService.getAllUserTask(id);
        CustomResponse<List<Task>> customResponse = new CustomResponse<>(0, userTasks);
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping
    public ResponseEntity<CustomResponse<List<Task>>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        CustomResponse<List<Task>> customResponse = new CustomResponse<>(0, tasks);
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Task>> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        CustomResponse<Task> customResponse = new CustomResponse<>(0, task);
        return ResponseEntity.ok(customResponse);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Task>> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        CustomResponse<Task> customResponse = new CustomResponse<>(0, createdTask);
        return ResponseEntity.ok(customResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Task>> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        CustomResponse<Task> customResponse = new CustomResponse<>(0, updatedTask);
        return ResponseEntity.ok(customResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Task>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        CustomResponse<Task> customResponse = new CustomResponse<>(0, null);
        return ResponseEntity.ok(customResponse);
    }
}
