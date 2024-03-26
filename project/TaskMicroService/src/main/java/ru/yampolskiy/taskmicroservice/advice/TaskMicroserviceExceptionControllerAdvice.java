package ru.yampolskiy.taskmicroservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yampolskiy.taskmicroservice.exception.TaskIdNotNullException;
import ru.yampolskiy.taskmicroservice.exception.TaskNotFoundException;
import ru.yampolskiy.taskmicroservice.model.CustomResponse;
import ru.yampolskiy.taskmicroservice.model.ExceptionData;
import ru.yampolskiy.taskmicroservice.model.Task;

@ControllerAdvice
public class TaskMicroserviceExceptionControllerAdvice {

    /**
     * Обрабатывает исключение TaskNotFoundException.
     * @param e Исключение TaskNotFoundException.
     * @return Ответ с информацией об исключении.
     */
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<CustomResponse<Task>> handleUserNotFoundException(TaskNotFoundException e) {
        ExceptionData exceptionData = new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        CustomResponse<Task> customResponse = new CustomResponse<>(1, exceptionData);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    /**
     * Обрабатывает исключение TaskIdNotNullException.
     * @param e Исключение TaskIdNotNullException.
     * @return Ответ с информацией об исключении.
     */
    @ExceptionHandler(TaskIdNotNullException.class)
    public ResponseEntity<CustomResponse<Task>> handleUserAlreadyExistsException(TaskIdNotNullException e) {
        ExceptionData exceptionData = new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        CustomResponse<Task> customResponse = new CustomResponse<>(1, exceptionData);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    /**
     * Обрабатывает исключение RuntimeException.
     * @param e Исключение RuntimeException.
     * @return Ответ с информацией об исключении.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomResponse<Task>> handleRuntimeException(RuntimeException e) {
        ExceptionData exceptionData = new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        CustomResponse<Task> customResponse = new CustomResponse<>(1, exceptionData);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    /**
     * Обрабатывает исключение Exception.
     * @param e Исключение Exception.
     * @return Ответ с информацией об исключении.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<Task>> handleException(Exception e) {
        ExceptionData exceptionData = new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        CustomResponse<Task> customResponse = new CustomResponse<>(1, exceptionData);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }
}
