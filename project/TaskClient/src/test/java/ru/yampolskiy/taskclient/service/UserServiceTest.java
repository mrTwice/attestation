package ru.yampolskiy.taskclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yampolskiy.taskclient.clients.UserClientApi;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.user.Role;
import ru.yampolskiy.taskclient.models.user.User;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserClientApi userClientApi;

    @Test
    void testFindUserByUserName_Success() throws JsonProcessingException {
        String username = "testuser";
        User user = new User(1L, "testuser", "testuser@example.com", "password123", true, Collections.singleton(Role.USER));
        CustomResponse<User> expectedResponse = new CustomResponse<>(0, user);

        when(userClientApi.findUserByUsername(username)).thenReturn(expectedResponse);

        CustomResponse<User> actualResponse = userService.findUserByUserName(username);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testRegisterNewUser_Success() throws JsonProcessingException {
        User newUser = new User(null, "newuser", "newuser@example.com", "password123", true, Collections.singleton(Role.USER));
        CustomResponse<User> expectedResponse = new CustomResponse<>(0, newUser);

        when(userClientApi.createUser(newUser)).thenReturn(expectedResponse);

        CustomResponse<User> actualResponse = userService.registerNewUser(newUser);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testDeleteAccount_Success() throws JsonProcessingException {
        Long userId = 1L;

        userService.deleteAccount(userId);
    }

    @Test
    void testUpdateAccount_Success() throws JsonProcessingException {
        Long userId = 1L;
        User updatedUser = new User(userId, "updateduser", "updateduser@example.com", "newpassword", true, Collections.singleton(Role.USER));
        CustomResponse<User> expectedResponse = new CustomResponse<>(0, updatedUser);

        when(userClientApi.updateUser(userId, updatedUser)).thenReturn(expectedResponse);

        CustomResponse<User> actualResponse = userService.updateAccount(userId, updatedUser);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testFindAllUsers_Success() throws JsonProcessingException {
        List<User> users = new ArrayList<>();
        User user1 = new User(1L, "user1", "user1@example.com", "password123", true, Collections.singleton(Role.USER));
        User user2 = new User(2L, "user2", "user2@example.com", "password456", true, Collections.singleton(Role.USER));
        users.add(user1);
        users.add(user2);
        CustomResponse<List<User>> expectedResponse = new CustomResponse<>(0, users);

        when(userClientApi.getUsers()).thenReturn(expectedResponse);

        CustomResponse<List<User>> actualResponse = userService.findAllUsers();
        assertEquals(expectedResponse, actualResponse);
    }
}

