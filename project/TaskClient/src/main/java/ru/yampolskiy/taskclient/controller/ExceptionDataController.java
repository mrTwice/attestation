package ru.yampolskiy.taskclient.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yampolskiy.taskclient.models.ExceptionData;

/**
 * Контроллер для обработки данных об исключениях.
 */
@Controller
public class ExceptionDataController {

    /**
     * Получает данные об исключении из сессии и отображает страницу с информацией об исключении.
     * @param model Модель представления.
     * @param session Сессия HTTP.
     * @return Шаблон страницы с данными об исключении.
     */
    @GetMapping("/exception-data")
    public String getExceptionData(Model model, HttpSession session) {
        // Получаем данные об исключении из сессии
        ExceptionData sessionData = (ExceptionData) session.getAttribute("exception");
        // Передаем данные об исключении в модель представления
        model.addAttribute("exceptionData", sessionData);
        return "exception";
    }
}
