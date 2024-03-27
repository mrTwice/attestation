package ru.yampolskiy.taskclient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.yampolskiy.taskclient.models.user.User;
import ru.yampolskiy.taskclient.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Отображает форму регистрации пользователя.
     * @param model Модель представления.
     * @return Шаблон страницы регистрации.
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    /**
     * Обрабатывает данные, отправленные из формы регистрации.
     * @param userForm Данные нового пользователя.
     * @return Перенаправление на страницу входа после успешной регистрации.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    @PostMapping("/register")
    public String registerUser(@Valid User userForm) throws JsonProcessingException {
        // Хэшируем пароль перед сохранением
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        userService.registerNewUser(userForm);
        return "redirect:/login";
    }
}
