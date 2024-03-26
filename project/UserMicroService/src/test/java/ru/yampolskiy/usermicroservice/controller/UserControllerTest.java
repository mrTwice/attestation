package ru.yampolskiy.usermicroservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.yampolskiy.usermicroservice.model.CustomResponse;
import ru.yampolskiy.usermicroservice.model.Role;
import ru.yampolskiy.usermicroservice.model.User;
import ru.yampolskiy.usermicroservice.service.UserService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<User> userList = Collections.singletonList(new User(1L, "testUser", "test@mail.ru", "password", true, Collections.singleton(Role.USER)));
        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<CustomResponse<List<User>>> responseEntity = userController.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().getErrorCode());
        assertEquals(userList, responseEntity.getBody().getResponseData());
    }

    @Test
    void testGetUserById() {
        long userId = 1L;
        User user = new User(1L, "testUser", "test@mail.ru", "password", true, Collections.singleton(Role.USER));
        when(userService.getUserById(userId)).thenReturn(user);

        ResponseEntity<CustomResponse<User>> responseEntity = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().getErrorCode());
        assertEquals(user, responseEntity.getBody().getResponseData());
    }

    @Test
    void testFindUserByUsername() {
        String username = "testUser";
        User user = new User(1L, "testUser", "test@mail.ru", "password", true, Collections.singleton(Role.USER));
        when(userService.findUserByUserName(username)).thenReturn(user);

        ResponseEntity<CustomResponse<User>> responseEntity = userController.findUserByUsername(username);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().getErrorCode());
        assertEquals(user, responseEntity.getBody().getResponseData());
    }

    @Test
    void testCreateUser() {
        User user = new User(1L, "testUser", "test@mail.ru", "password", true, Collections.singleton(Role.USER));
        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<CustomResponse<User>> responseEntity = userController.createUser(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().getErrorCode());
        assertEquals(user, responseEntity.getBody().getResponseData());
    }

    @Test
    void testUpdateUser() {
        long userId = 1L;
        User user = new User(1L, "testUser", "test@mail.ru", "password", true, Collections.singleton(Role.USER));
        when(userService.updateUser(userId, user)).thenReturn(user);

        ResponseEntity<CustomResponse<User>> responseEntity = userController.updateUser(userId, user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().getErrorCode());
        assertEquals(user, responseEntity.getBody().getResponseData());
    }

    @Test
    void testDeleteUser() {
        long userId = 1L;

        ResponseEntity<CustomResponse<User>> responseEntity = userController.deleteUser(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().getErrorCode());
        assertNull(responseEntity.getBody().getResponseData()); // Проверяем, что данные равны null

        verify(userService, times(1)).deleteUser(userId);
    }
}
