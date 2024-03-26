package ru.yampolskiy.taskmicroservice.advice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.yampolskiy.taskmicroservice.exception.TaskIdNotNullException;
import ru.yampolskiy.taskmicroservice.exception.TaskNotFoundException;
import ru.yampolskiy.taskmicroservice.model.CustomResponse;
import ru.yampolskiy.taskmicroservice.model.ExceptionData;
import ru.yampolskiy.taskmicroservice.model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TaskMicroserviceExceptionControllerAdviceTest {

    @Mock
    private TaskNotFoundException taskNotFoundException;

    @Mock
    private TaskIdNotNullException taskIdNotNullException;

    @InjectMocks
    private TaskMicroserviceExceptionControllerAdvice controllerAdvice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testHandleUserNotFoundException() {
        String errorMessage = "Task not found";
        when(taskNotFoundException.getMessage()).thenReturn(errorMessage);

        ResponseEntity<CustomResponse<Task>> responseEntity = controllerAdvice.handleUserNotFoundException(taskNotFoundException);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CustomResponse<Task> responseBody = responseEntity.getBody();
        assertEquals(1, responseBody.getErrorCode());
        ExceptionData exceptionData = responseBody.getResponseError();
        assertEquals(TaskNotFoundException.class.getPackage().getName(), exceptionData.getPackageName());
        assertEquals(TaskNotFoundException.class.getSimpleName(), exceptionData.getExceptionType());
        assertEquals(errorMessage, exceptionData.getMessage());
    }

    @Test
    void testHandleUserAlreadyExistsException() {
        String errorMessage = "Task ID must be null";
        when(taskIdNotNullException.getMessage()).thenReturn(errorMessage);

        ResponseEntity<CustomResponse<Task>> responseEntity = controllerAdvice.handleUserAlreadyExistsException(taskIdNotNullException);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CustomResponse<Task> responseBody = responseEntity.getBody();
        assertEquals(1, responseBody.getErrorCode());
        ExceptionData exceptionData = responseBody.getResponseError();
        assertEquals(TaskIdNotNullException.class.getPackage().getName(), exceptionData.getPackageName());
        assertEquals(TaskIdNotNullException.class.getSimpleName(), exceptionData.getExceptionType());
        assertEquals(errorMessage, exceptionData.getMessage());
    }

    @Test
    void testHandleRuntimeException() {
        String errorMessage = "Runtime error occurred";
        RuntimeException runtimeException = new RuntimeException(errorMessage);

        ResponseEntity<CustomResponse<Task>> responseEntity = controllerAdvice.handleRuntimeException(runtimeException);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CustomResponse<Task> responseBody = responseEntity.getBody();
        assertEquals(1, responseBody.getErrorCode());
        ExceptionData exceptionData = responseBody.getResponseError();
        assertEquals(RuntimeException.class.getPackage().getName(), exceptionData.getPackageName());
        assertEquals(RuntimeException.class.getSimpleName(), exceptionData.getExceptionType());
        assertEquals(errorMessage, exceptionData.getMessage());
    }

    @Test
    void testHandleException() {
        String errorMessage = "Unknown error occurred";
        Exception exception = new Exception(errorMessage);

        ResponseEntity<CustomResponse<Task>> responseEntity = controllerAdvice.handleException(exception);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CustomResponse<Task> responseBody = responseEntity.getBody();
        assertEquals(1, responseBody.getErrorCode());
        ExceptionData exceptionData = responseBody.getResponseError();
        assertEquals(Exception.class.getPackage().getName(), exceptionData.getPackageName());
        assertEquals(Exception.class.getSimpleName(), exceptionData.getExceptionType());
        assertEquals(errorMessage, exceptionData.getMessage());
    }
}
