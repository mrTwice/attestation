package ru.yampolskiy.taskclient.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yampolskiy.taskclient.models.ExceptionData;

@ControllerAdvice
public class TaskManagerExceptionControllerAdvice {
    @ExceptionHandler(JsonProcessingException.class)
    public String handleJsonProcessingException(JsonProcessingException e, HttpSession session) {
        session.setAttribute("exception", new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage()));
        return "redirect:/exception-data";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpSession session) {
        session.setAttribute("exception", new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage()));
        return "redirect:/exception-data";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(Exception e, HttpSession session) {
        session.setAttribute("exception", new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage()));
        return "redirect:/exception-data";
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleUsernameNotFoundException(Exception e, HttpSession session) {
        session.setAttribute("exception", new ExceptionData(e.getClass().getPackage().getName(), e.getClass().getSimpleName(), e.getMessage()));
        return "redirect:/exception-data";
    }
}
