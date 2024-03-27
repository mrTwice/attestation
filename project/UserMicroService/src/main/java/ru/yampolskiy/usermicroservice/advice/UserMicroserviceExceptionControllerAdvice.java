package ru.yampolskiy.usermicroservice.advice;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yampolskiy.usermicroservice.exception.UserAlreadyExistsException;
import ru.yampolskiy.usermicroservice.exception.UserNotFoundException;
import ru.yampolskiy.usermicroservice.model.CustomResponse;
import ru.yampolskiy.usermicroservice.model.ExceptionData;
import ru.yampolskiy.usermicroservice.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Обработчик исключений для пользовательского микросервиса.
 */
@ControllerAdvice
public class UserMicroserviceExceptionControllerAdvice {

    /**
     * Обработчик исключения, когда пользователь не найден.
     * @param e исключение UserNotFoundException
     * @return ответ с сообщением об ошибке
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomResponse<User>> handleUserNotFoundException(UserNotFoundException e) {
        ExceptionData exceptionData = new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        CustomResponse<User> customResponse = new CustomResponse<>(1, exceptionData);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    /**
     * Обработчик исключения, когда пользователь уже существует.
     * @param e исключение UserAlreadyExistsException
     * @return ответ с сообщением об ошибке
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<CustomResponse<User>> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        ExceptionData exceptionData = new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        CustomResponse<User> customResponse = new CustomResponse<>(1, exceptionData);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    /**
     * Обработчик остальных исключений типа RuntimeException.
     * @param e исключение RuntimeException
     * @return ответ с сообщением об ошибке
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomResponse<User>> handleRuntimeException(RuntimeException e) {
        ExceptionData exceptionData = new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        CustomResponse<User> customResponse = new CustomResponse<>(1, exceptionData);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    /**
     * Обработчик остальных исключений.
     * @param e исключение Exception
     * @return ответ с сообщением об ошибке
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<User>> handleException(Exception e) {
        ExceptionData exceptionData = new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        CustomResponse<User> customResponse = new CustomResponse<>(1, exceptionData);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    /**
     * Обработчик остальных исключений типа MethodArgumentNotValidException.
     * @param e исключение Exception
     * @return ответ с сообщением об ошибке
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponse<User>> handleValidationExceptions(MethodArgumentNotValidException e) {
        ExceptionData exceptionData = new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage());
        CustomResponse<User> customResponse = new CustomResponse<>(1, exceptionData);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }
}
