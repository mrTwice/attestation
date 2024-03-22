package ru.yampolskiy.taskclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yampolskiy.taskclient.clients.UserClientApi;
import ru.yampolskiy.taskclient.models.user.User;
import ru.yampolskiy.taskclient.models.user.UserPrincipal;

import java.util.List;

@Service
public class UserService{

    @Autowired
    private UserClientApi userClientApi;

    public User findUserByUserName(String username){
        return userClientApi.findUserByUsername(username);
    }

    public User registerNewUser(User user){
        return userClientApi.createUser(user);
    }

    public void deleteAccount(Long id){
        userClientApi.deleteUser(id);
    }

    public User updateAccount(Long id, User user){
        return userClientApi.updateUser(id, user);
    }

    public List<User> findAllUsers(){
        return userClientApi.getUsers();
    }

}
