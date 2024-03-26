package ru.yampolskiy.taskmicroservice.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Исключение, выбрасываемое при попытке доступа к задаче, которая не существует.
 */
@Getter
@Setter
public class TaskNotFoundException extends RuntimeException {

    /** Идентификатор задачи, которой не существует. */
    private Long id;

    /** Сообщение об ошибке. */
    private String message;

    /**
     * Конструктор с указанием идентификатора задачи.
     * @param id идентификатор задачи
     */
    public TaskNotFoundException(Long id) {
        this.id = id;
        this.message = "Задача с id = " + id + "  не найдена.";
    }
}
