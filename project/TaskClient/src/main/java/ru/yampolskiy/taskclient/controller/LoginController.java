package ru.yampolskiy.taskclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для страницы входа.
 */
@Controller
public class LoginController {

    /**
     * Отображает страницу входа.
     * @return Шаблон страницы входа.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
