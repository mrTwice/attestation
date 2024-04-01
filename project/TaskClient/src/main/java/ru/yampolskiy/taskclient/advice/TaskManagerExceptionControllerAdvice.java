package ru.yampolskiy.taskclient.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yampolskiy.taskclient.models.ExceptionData;

/**
 * Контроллер Advice для обработки исключений в приложении Task Manager.
 */
@ControllerAdvice
public class TaskManagerExceptionControllerAdvice {

    /**
     * Обрабатывает исключение UsernameNotFoundException.
     * @param e Исключение UsernameNotFoundException.
     * @param session Сессия HTTP.
     * @return Перенаправление на страницу с данными об исключении.
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleUsernameNotFoundException(UsernameNotFoundException e, HttpSession session) {
        session.setAttribute("exception", new ExceptionData(
                e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage())
        );
        return "redirect:/exception-data";
    }

    /**
     * Обрабатывает исключение MethodArgumentNotValidException.
     * @param e Исключение MethodArgumentNotValidException.
     * @param session Сессия HTTP.
     * @return Перенаправление на страницу с данными об исключении.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException e, HttpSession session) {
        session.setAttribute("exception", new ExceptionData(
                e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage())
        );
        return "redirect:/exception-data";
    }

    /**
     * Обрабатывает исключение JsonProcessingException.
     * @param e Исключение JsonProcessingException.
     * @param session Сессия HTTP.
     * @return Перенаправление на страницу с данными об исключении.
     */
    @ExceptionHandler(JsonProcessingException.class)
    public String handleJsonProcessingException(JsonProcessingException e, HttpSession session) {
        session.setAttribute("exception", new ExceptionData(
                e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage())
        );
        return "redirect:/exception-data";
    }

    /**
     * Обрабатывает исключение RuntimeException.
     * @param e Исключение RuntimeException.
     * @param session Сессия HTTP.
     * @return Перенаправление на страницу с данными об исключении.
     */
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, HttpSession session) {
        session.setAttribute("exception", new ExceptionData(
                e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage())
        );
        return "redirect:/exception-data";
    }

    /**
     * Обрабатывает исключение Exception.
     * @param e Исключение Exception.
     * @param session Сессия HTTP.
     * @return Перенаправление на страницу с данными об исключении.
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpSession session) {
        session.setAttribute("exception", new ExceptionData(
                e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage())
        );
        return "redirect:/exception-data";
    }

}
