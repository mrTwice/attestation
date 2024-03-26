package ru.yampolskiy.taskclient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.user.User;
import ru.yampolskiy.taskclient.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistrationController registrationController;

    @Test
    void testShowRegistrationForm() {
        Model model = Mockito.mock(Model.class);
        String result = registrationController.showRegistrationForm(model);
        assertEquals("registration", result);
    }

    @Test
    void testRegisterUser() throws JsonProcessingException {
        String passwordHash = "$2a$10$2XjEftbxtk9IAswPqawC6uOkOe7/T6/TtNvGWbcSVR6BkUXPdo4bC";
        User userForm = new User("username", "email@example.com", passwordHash, true);

        Mockito.when(userService.registerNewUser(any(User.class))).thenReturn(new CustomResponse<>(0, userForm, null));
        Mockito.when(passwordEncoder.encode(userForm.getPassword())).thenReturn(passwordHash);

        String result = registrationController.registerUser(userForm);
        assertEquals("redirect:/login", result);

        // Проверяем, что пароль был хэширован перед сохранением
        verify(userService).registerNewUser(any(User.class));
        verify(passwordEncoder).encode(userForm.getPassword());
    }
}
