package ru.yampolskiy.taskmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yampolskiy.taskmicroservice.exception.TaskIdNotNullException;
import ru.yampolskiy.taskmicroservice.exception.TaskNotFoundException;
import ru.yampolskiy.taskmicroservice.model.Task;
import ru.yampolskiy.taskmicroservice.model.TaskStatus;
import ru.yampolskiy.taskmicroservice.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getAllUserTask(Long id) {
        return taskRepository.findAllByOwnerId(id).orElse(Collections.emptyList());
    }

    public Task getTaskById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task createTask(Task task) {
        if (task.getId() != null) {
            throw new TaskIdNotNullException(task);
        }
        task.setCreated(LocalDateTime.now());
        task.setStatus(TaskStatus.OPEN);
        task.setLastUpdate(task.getCreated());
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        task.setId(id);
        task.setLastUpdate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
}
