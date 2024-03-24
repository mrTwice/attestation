package ru.yampolskiy.taskclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yampolskiy.taskclient.clients.UserClientApi;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.user.User;
import ru.yampolskiy.taskclient.models.user.UserPrincipal;

import java.util.List;

@Service
public class UserService{

    @Autowired
    private UserClientApi userClientApi;

    public CustomResponse<User> findUserByUserName(String username) throws JsonProcessingException {
        return userClientApi.findUserByUsername(username);
    }

    public CustomResponse<User> registerNewUser(User user) throws JsonProcessingException {
        return userClientApi.createUser(user);
    }

    public void deleteAccount(Long id) throws JsonProcessingException {
        userClientApi.deleteUser(id);
    }

    public CustomResponse<User> updateAccount(Long id, User user) throws JsonProcessingException {
        return userClientApi.updateUser(id, user);
    }

    public CustomResponse<List<User>> findAllUsers() throws JsonProcessingException {
        return userClientApi.getUsers();
    }

}
