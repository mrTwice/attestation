package ru.yampolskiy.usermicroservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.yampolskiy.usermicroservice.exception.UserAlreadyExistsException;
import ru.yampolskiy.usermicroservice.exception.UserNotFoundException;
import ru.yampolskiy.usermicroservice.model.User;
import ru.yampolskiy.usermicroservice.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<User> userList = Collections.singletonList(new User());
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        assertEquals(userList, result);
    }

    @Test
    void testGetUserById() {
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findById(userId)).thenReturn(optionalUser);

        User result = userService.getUserById(userId);

        assertEquals(user, result);
    }

    @Test
    void testGetUserById_UserNotFoundException() {
        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }

    @Test
    void testFindUserByUserName() {
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findUserByUsername(username)).thenReturn(optionalUser);

        User result = userService.findUserByUserName(username);

        assertEquals(user, result);
    }

    @Test
    void testFindUserByUserName_UserNotFoundException() {
        String username = "nonexistentUser";
        when(userRepository.findUserByUsername(username)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findUserByUserName(username));
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setUsername("testUser");
        when(userRepository.existsUserByUsername(user.getUsername())).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals(user.getUsername(), result.getUsername());
    }

    @Test
    void testCreateUser_UserAlreadyExistsException() {
        User user = new User();
        user.setUsername("existingUser");
        when(userRepository.existsUserByUsername(user.getUsername())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(user));
    }

    @Test
    void testUpdateUser() {
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.updateUser(userId, user);

        assertNotNull(result);
        assertEquals(userId, result.getId());
    }

    @Test
    void testUpdateUser_UserNotFoundException() {
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userId, user));
    }

    @Test
    void testDeleteUser() {
        long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);

        assertDoesNotThrow(() -> userService.deleteUser(userId));
        verify(userRepository, times(1)).deleteUserById(userId);
    }

    @Test
    void testDeleteUser_UserNotFoundException() {
        long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
    }
}
