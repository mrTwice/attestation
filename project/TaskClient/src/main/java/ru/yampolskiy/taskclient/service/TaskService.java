package ru.yampolskiy.taskclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yampolskiy.taskclient.clients.TaskClientApi;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.task.Task;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskClientApi taskClientApi;

    public CustomResponse<Task> createNewTask(Task task) throws JsonProcessingException {
        return taskClientApi.createTask(task);
    }

    public CustomResponse<Task>  deleteTask(Long id) throws JsonProcessingException {
        return taskClientApi.deleteTask(id);
    }

    public CustomResponse<Task> updateTask(Long id, Task task) throws JsonProcessingException {
        return taskClientApi.updateTask(id, task);
    }

    public CustomResponse<Task> findTaskById(Long id) throws JsonProcessingException {
        return taskClientApi.getTaskById(id);
    }

    public CustomResponse<List<Task>> findAllUserTasks(Long userId) throws JsonProcessingException {
        return taskClientApi.getUserTasks(userId);
    }

    public CustomResponse<List<Task>> findAllTask() throws JsonProcessingException {
        return taskClientApi.getAllTasks();
    }

}
