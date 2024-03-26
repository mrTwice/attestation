package ru.yampolskiy.taskclient.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.ExceptionData;
import ru.yampolskiy.taskclient.models.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void testLoadUserByUsername_UserExists() throws JsonProcessingException {
        // Создаем пользователя для теста
        User user = new User("testUser", "test@example.com", "password", true);

        // Задаем ожидаемый результат от вызова сервиса UserService
        CustomResponse<User> customResponse = new CustomResponse<>(0, user);
        Mockito.when(userService.findUserByUserName("testUser")).thenReturn(customResponse);

        // Вызываем метод loadUserByUsername и проверяем, что он возвращает UserDetails
        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");
        assertEquals(user.getUsername(), userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() throws JsonProcessingException {
        // Задаем ожидаемый результат от вызова сервиса UserService - пользователь не найден
        CustomResponse<User> customResponse = new CustomResponse<>(1, null, new ExceptionData("Package", "Exception", "UserNotExist"));
        Mockito.when(userService.findUserByUserName("nonExistingUser")).thenReturn(customResponse);

        // Вызываем метод loadUserByUsername и ожидаем исключение UsernameNotFoundException
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("nonExistingUser");
        });
    }
}
