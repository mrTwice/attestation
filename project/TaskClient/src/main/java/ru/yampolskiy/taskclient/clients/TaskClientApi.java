package ru.yampolskiy.taskclient.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yampolskiy.taskclient.models.task.Task;

import java.util.List;

@FeignClient(name = "tasks", url = "${apigateway.base-url}/tasks")
public interface TaskClientApi {

    @GetMapping("/user/{id}")
    ResponseEntity<List<Task>> getUserTasks (@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<Task>> getAllTasks();

    @GetMapping("/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Task> createTask(@RequestBody Task task);

    @PutMapping("/{id}")
    ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable Long id);
}
