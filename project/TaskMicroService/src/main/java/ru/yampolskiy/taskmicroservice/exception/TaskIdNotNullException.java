package ru.yampolskiy.taskmicroservice.exception;

import ru.yampolskiy.taskmicroservice.model.Task;

public class TaskIdNotNullException extends RuntimeException{
    private Task task;
    private String message;

    public TaskIdNotNullException(String message) {
        super(message);
    }

    public TaskIdNotNullException(Task task) {
        this.task = task;
        this.message = "Создаваемая задача не должна иметь id. ID создаваемой задачи: " + task.getId();
    }
}
