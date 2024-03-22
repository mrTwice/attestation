package ru.yampolskiy.taskclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yampolskiy.taskclient.clients.TaskClientApi;
import ru.yampolskiy.taskclient.models.task.Task;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskClientApi taskClientApi;

    public Task createNewTask(Task task){
        return taskClientApi.createTask(task).getBody();
    }

    public void  deleteTask(Long id){
        taskClientApi.deleteTask(id);
    }

    public Task updateTask(Long id, Task task){
        return taskClientApi.updateTask(id, task).getBody();
    }

    public Task findTaskById(Long id){
        return taskClientApi.getTaskById(id).getBody();
    }

    public List<Task> findAllUserTasks(Long userId){
        return taskClientApi.getUserTasks(userId).getBody();
    }

    public List<Task> findAllTask(){
        return taskClientApi.getAllTasks().getBody();
    }

}
