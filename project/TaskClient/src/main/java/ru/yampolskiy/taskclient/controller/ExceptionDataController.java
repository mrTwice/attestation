package ru.yampolskiy.taskclient.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yampolskiy.taskclient.models.ExceptionData;

@Controller
public class ExceptionDataController {

    @GetMapping("/exception-data")
    public String getExceptionData(Model model, HttpSession session) {
        ExceptionData sessionData = (ExceptionData) session.getAttribute("exception");
        model.addAttribute("exceptionData", sessionData);
        return "exception";
    }
}
