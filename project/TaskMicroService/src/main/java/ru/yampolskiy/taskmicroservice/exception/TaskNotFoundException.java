package ru.yampolskiy.taskmicroservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskNotFoundException extends RuntimeException{
    private Long id;
    private String message;

    public TaskNotFoundException(Long id){
        this.id = id;
        this.message = "Задача с id = " + id + "  не найдена.";
    }
}
