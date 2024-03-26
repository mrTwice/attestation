package ru.yampolskiy.usermicroservice.advice;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.yampolskiy.usermicroservice.exception.UserAlreadyExistsException;
import ru.yampolskiy.usermicroservice.exception.UserNotFoundException;
import ru.yampolskiy.usermicroservice.model.CustomResponse;
import ru.yampolskiy.usermicroservice.model.ExceptionData;
import ru.yampolskiy.usermicroservice.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMicroserviceExceptionControllerAdviceTest {

    private final UserMicroserviceExceptionControllerAdvice advice = new UserMicroserviceExceptionControllerAdvice();

    @Test
    void handleUserNotFoundException() {
        UserNotFoundException exception = new UserNotFoundException(1L);
        ResponseEntity<CustomResponse<User>> responseEntity = advice.handleUserNotFoundException(exception);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().getErrorCode());
        ExceptionData exceptionData = (ExceptionData) responseEntity.getBody().getResponseError();
        assertEquals(exception.getClass().getPackage().getName(), exceptionData.getPackageName());
        assertEquals(exception.getClass().getSimpleName(), exceptionData.getExceptionType());
        assertEquals(exception.getMessage(), exceptionData.getMessage());
    }

    @Test
    void handleUserAlreadyExistsException() {
        UserAlreadyExistsException exception = new UserAlreadyExistsException(new User());
        ResponseEntity<CustomResponse<User>> responseEntity = advice.handleUserAlreadyExistsException(exception);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().getErrorCode());
        ExceptionData exceptionData = (ExceptionData) responseEntity.getBody().getResponseError();
        assertEquals(exception.getClass().getPackage().getName(), exceptionData.getPackageName());
        assertEquals(exception.getClass().getSimpleName(), exceptionData.getExceptionType());
        assertEquals(exception.getMessage(), exceptionData.getMessage());
    }

    @Test
    void handleRuntimeException() {
        RuntimeException exception = new RuntimeException("Test runtime exception");
        ResponseEntity<CustomResponse<User>> responseEntity = advice.handleRuntimeException(exception);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().getErrorCode());
        ExceptionData exceptionData = (ExceptionData) responseEntity.getBody().getResponseError();
        assertEquals(exception.getClass().getPackage().getName(), exceptionData.getPackageName());
        assertEquals(exception.getClass().getSimpleName(), exceptionData.getExceptionType());
        assertEquals(exception.getMessage(), exceptionData.getMessage());
    }

    @Test
    void handleException() {
        Exception exception = new Exception("Test exception");
        ResponseEntity<CustomResponse<User>> responseEntity = advice.handleException(exception);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().getErrorCode());
        ExceptionData exceptionData = (ExceptionData) responseEntity.getBody().getResponseError();
        assertEquals(exception.getClass().getPackage().getName(), exceptionData.getPackageName());
        assertEquals(exception.getClass().getSimpleName(), exceptionData.getExceptionType());
        assertEquals(exception.getMessage(), exceptionData.getMessage());
    }
}
