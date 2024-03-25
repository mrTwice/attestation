package ru.yampolskiy.taskclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yampolskiy.taskclient.models.user.User;
import ru.yampolskiy.taskclient.models.user.UserPrincipal;

/**
 * Реализация интерфейса UserDetailsService для аутентификации пользователей.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * Загружает пользователя по его имени пользователя для аутентификации.
     * @param username Имя пользователя.
     * @return UserDetails, представляющий пользователя для аутентификации.
     * @throws UsernameNotFoundException Если пользователь с указанным именем не найден.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            // Получаем пользователя по его имени пользователя
            User user = userService.findUserByUserName(username).getResponseData();
            // Возвращаем UserDetails, используя объект UserPrincipal
            return new UserPrincipal(user);
        } catch (UsernameNotFoundException | JsonProcessingException e) {
            // Если пользователь не найден или возникает ошибка при обработке JSON, выбрасываем исключение
            throw new UsernameNotFoundException("Пользователь с именем " + username + " не найден");
        }
    }
}
