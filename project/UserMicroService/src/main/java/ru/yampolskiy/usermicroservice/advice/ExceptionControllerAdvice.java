package ru.yampolskiy.usermicroservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yampolskiy.usermicroservice.exception.UserAlreadyExistsException;
import ru.yampolskiy.usermicroservice.exception.UserNotFoundException;
import ru.yampolskiy.usermicroservice.model.ExceptionResponse;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }
}
