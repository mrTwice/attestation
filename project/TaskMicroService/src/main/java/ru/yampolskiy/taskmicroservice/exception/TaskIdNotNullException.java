package ru.yampolskiy.taskmicroservice.exception;

import ru.yampolskiy.taskmicroservice.model.Task;

/**
 * Исключение, выбрасываемое при попытке создания задачи с непустым идентификатором.
 */
public class TaskIdNotNullException extends RuntimeException {

    /** Задача, для которой было выброшено исключение. */
    private Task task;

    /** Сообщение об ошибке. */
    private String message;

    /**
     * Конструктор с сообщением.
     * @param message сообщение об ошибке
     */
    public TaskIdNotNullException(String message) {
        super(message);
    }

    /**
     * Конструктор с задачей.
     * @param task задача, для которой выброшено исключение
     */
    public TaskIdNotNullException(Task task) {
        this.task = task;
        this.message = "Создаваемая задача не должна иметь id. ID создаваемой задачи: " + task.getId();
    }
}
