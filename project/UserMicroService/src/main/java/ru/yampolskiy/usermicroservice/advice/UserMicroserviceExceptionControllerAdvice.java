package ru.yampolskiy.usermicroservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yampolskiy.usermicroservice.exception.UserAlreadyExistsException;
import ru.yampolskiy.usermicroservice.exception.UserNotFoundException;
import ru.yampolskiy.usermicroservice.model.CustomResponse;
import ru.yampolskiy.usermicroservice.model.ExceptionData;
import ru.yampolskiy.usermicroservice.model.User;

@ControllerAdvice
public class UserMicroserviceExceptionControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomResponse<User>> handleUserNotFoundException(UserNotFoundException e) {
        ExceptionData exceptionData = new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        CustomResponse<User> customResponse = new CustomResponse<>(1, exceptionData);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<CustomResponse<User>> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        ExceptionData exceptionData = new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        CustomResponse<User> customResponse = new CustomResponse<>(1, exceptionData);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomResponse<User>> handleRuntimeException(RuntimeException e) {
        ExceptionData exceptionData = new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        CustomResponse<User> customResponse = new CustomResponse<>(1, exceptionData);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<User>> handleException(Exception e) {
        ExceptionData exceptionData = new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        CustomResponse<User> customResponse = new CustomResponse<>(1, exceptionData);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }
}
