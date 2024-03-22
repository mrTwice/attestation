package ru.yampolskiy.usermicroservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.yampolskiy.usermicroservice.model.Role;
import ru.yampolskiy.usermicroservice.model.User;
import ru.yampolskiy.usermicroservice.service.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User testUser;

    @BeforeEach
    public void setUp(){
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("petrucho");
        testUser.setRoles(Collections.singleton(Role.USER));
        testUser.setPassword("qwerty");
        testUser.setEmail("petrucho@mail.ru");

    }

    @Test
    public void testGetAllUsers() {
        List<User> users = Arrays.asList(testUser);
        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    public void testGetUserById() {
        when(userService.getUserById(1L)).thenReturn(testUser);

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testFindUserByUsername() {
        when(userService.findUserByUserName("Alice")).thenReturn(testUser);

        ResponseEntity<User> response = userController.findUserByUsername("Alice");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testCreateUser() {
        when(userService.createUser(any(User.class))).thenReturn(testUser);

        ResponseEntity<User> response = userController.createUser(testUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testUpdateUser() {
        when(userService.updateUser(1L, testUser)).thenReturn(testUser);

        ResponseEntity<User> response = userController.updateUser(1L, testUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testDeleteUser() {
        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}