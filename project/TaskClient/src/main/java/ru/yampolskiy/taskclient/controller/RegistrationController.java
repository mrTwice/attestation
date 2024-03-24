package ru.yampolskiy.taskclient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(User userForm) throws JsonProcessingException {
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        userService.registerNewUser(userForm);
        return "redirect:/login";
    }
}
