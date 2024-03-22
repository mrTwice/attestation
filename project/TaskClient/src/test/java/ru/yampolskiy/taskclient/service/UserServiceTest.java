package ru.yampolskiy.taskclient.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.yampolskiy.taskclient.models.user.User;
import ru.yampolskiy.taskclient.clients.UserClientApi;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserClientApi userClientApi;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindUserByUserName() {
        String username = "testUser";
        User user = new User();
        when(userClientApi.findUserByUsername(username)).thenReturn(user);

        User result = userService.findUserByUserName(username);

        assertThat(result).isEqualTo(user);
    }

    @Test
    public void testRegisterNewUser() {

        User user = new User();
        when(userClientApi.createUser(user)).thenReturn(user);

        User result = userService.registerNewUser(user);

        assertThat(result).isEqualTo(user);
    }

    @Test
    public void testDeleteAccount() {

        Long userId = 1L;
        doNothing().when(userClientApi).deleteUser(userId);

        userService.deleteAccount(userId);

        verify(userClientApi, times(1)).deleteUser(userId);
    }

    @Test
    public void testUpdateAccount() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        when(userClientApi.updateUser(userId, user)).thenReturn(user);

        User result = userService.updateAccount(userId, user);

        assertThat(result).isEqualTo(user);
    }

    @Test
    public void testFindAllUsers() {

        List<User> users = Collections.singletonList(new User());
        when(userClientApi.getUsers()).thenReturn(users);

        List<User> result = userService.findAllUsers();

        assertThat(result).isEqualTo(users);
    }
}
