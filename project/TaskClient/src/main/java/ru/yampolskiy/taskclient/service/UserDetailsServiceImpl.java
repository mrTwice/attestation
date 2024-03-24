package ru.yampolskiy.taskclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yampolskiy.taskclient.models.user.User;
import ru.yampolskiy.taskclient.models.user.UserPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findUserByUserName(username).getResponseData();
            return new UserPrincipal(user);
        } catch (UsernameNotFoundException | JsonProcessingException e) {

            throw new UsernameNotFoundException("Пользователь с именем " + username + " не найден");
        }
    }
}
