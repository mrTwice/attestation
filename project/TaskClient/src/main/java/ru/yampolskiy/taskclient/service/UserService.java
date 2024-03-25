package ru.yampolskiy.taskclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yampolskiy.taskclient.clients.UserClientApi;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.user.User;

import java.util.List;

/**
 * Сервис для работы с пользователями.
 */
@Service
public class UserService {

    @Autowired
    private UserClientApi userClientApi;

    /**
     * Находит пользователя по его имени пользователя.
     * @param username Имя пользователя.
     * @return Ответ сервера с найденным пользователем.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<User> findUserByUserName(String username) throws JsonProcessingException {
        return userClientApi.findUserByUsername(username);
    }

    /**
     * Регистрирует нового пользователя.
     * @param user Новый пользователь для регистрации.
     * @return Ответ сервера с созданным пользователем.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<User> registerNewUser(User user) throws JsonProcessingException {
        return userClientApi.createUser(user);
    }

    /**
     * Удаляет аккаунт пользователя.
     * @param id Идентификатор пользователя.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public void deleteAccount(Long id) throws JsonProcessingException {
        userClientApi.deleteUser(id);
    }

    /**
     * Обновляет данные аккаунта пользователя.
     * @param id Идентификатор пользователя.
     * @param user Новые данные пользователя.
     * @return Ответ сервера с обновленными данными пользователя.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<User> updateAccount(Long id, User user) throws JsonProcessingException {
        return userClientApi.updateUser(id, user);
    }

    /**
     * Получает список всех пользователей.
     * @return Ответ сервера с списком пользователей.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<List<User>> findAllUsers() throws JsonProcessingException {
        return userClientApi.getUsers();
    }
}
