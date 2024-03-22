package ru.yampolskiy.taskmicroservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yampolskiy.taskmicroservice.exception.TaskIdNotNullException;
import ru.yampolskiy.taskmicroservice.exception.TaskNotFoundException;
import ru.yampolskiy.taskmicroservice.model.ExceptionResponse;

@ControllerAdvice
public class TaskMicroserviceExceptionControllerAdvice {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(TaskNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }

    @ExceptionHandler(TaskIdNotNullException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(TaskIdNotNullException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }
}
