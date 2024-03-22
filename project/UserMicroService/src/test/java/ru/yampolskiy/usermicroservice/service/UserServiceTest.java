package ru.yampolskiy.usermicroservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.yampolskiy.usermicroservice.model.Role;
import ru.yampolskiy.usermicroservice.model.User;
import ru.yampolskiy.usermicroservice.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

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
    void getAllUsers() {
        Iterable<User> userList = userRepository.findAll();
        assertThat(userList).isEmpty();

    }

    @Test
    void getUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        User user = userService.getUserById(1L);
        assertThat(user).isEqualTo(testUser);

    }

    @Test
    void findUserByUserName() {
        when(userRepository.findUserByUsername("petrucho")).thenReturn(Optional.of(testUser));
        User user = userService.findUserByUserName("petrucho");
        assertThat(user).isEqualTo(testUser);
    }

    @Test
    void createUser() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        User user = userService.createUser(testUser);
        assertThat(user).isEqualTo(testUser);
        verify(userRepository, times(1)).save(any(User.class));


    }

    @Test
    void updateUser() {

        when(userRepository.existsById(testUser.getId())).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User updatedUser = userService.updateUser(testUser.getId(), testUser);
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getUsername()).isEqualTo(testUser.getUsername());
        assertThat(updatedUser.getPassword()).isEqualTo(testUser.getPassword());

        verify(userRepository, times(1)).existsById(testUser.getId());
        verify(userRepository, times(1)).save(any(User.class));


    }

    @Test
    void deleteUser() {
        when(userRepository.existsById(testUser.getId())).thenReturn(true);
        userService.deleteUser(testUser.getId());
        verify(userRepository, times(1)).existsById(testUser.getId());
        verify(userRepository, times(1)).deleteById(testUser.getId());
    }

}