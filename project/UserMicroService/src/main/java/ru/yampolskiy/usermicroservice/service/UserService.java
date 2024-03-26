package ru.yampolskiy.usermicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yampolskiy.usermicroservice.exception.UserAlreadyExistsException;
import ru.yampolskiy.usermicroservice.exception.UserNotFoundException;
import ru.yampolskiy.usermicroservice.model.Role;
import ru.yampolskiy.usermicroservice.model.User;
import ru.yampolskiy.usermicroservice.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с пользователями.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Получить всех пользователей.
     * @return Список всех пользователей.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Получить пользователя по его идентификатору.
     * @param id Идентификатор пользователя.
     * @return Найденный пользователь.
     * @throws UserNotFoundException если пользователь с указанным идентификатором не найден.
     */
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Найти пользователя по его имени пользователя.
     * @param username Имя пользователя.
     * @return Найденный пользователь.
     * @throws UserNotFoundException если пользователь с указанным именем не найден.
     */
    public User findUserByUserName(String username){
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        return optionalUser.orElseThrow(() -> new UserNotFoundException("Пользователь "+ username +" не найден"));
    }

    /**
     * Создать нового пользователя.
     * @param user Данные нового пользователя.
     * @return Созданный пользователь.
     * @throws UserAlreadyExistsException если пользователь с таким именем уже существует.
     */
    public User createUser(User user) {
        if (userRepository.existsUserByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(user);
        }
        user.setId(null);
        user.setActive(true);
        if(user.getRoles() == null)
            user.setRoles(Collections.singleton(Role.USER));
        return userRepository.save(user);
    }

    /**
     * Обновить данные пользователя.
     * @param id Идентификатор пользователя для обновления.
     * @param user Новые данные пользователя.
     * @return Обновленный пользователь.
     * @throws UserNotFoundException если пользователь с указанным идентификатором не найден.
     */
    public User updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        return userRepository.save(user);
    }

    /**
     * Удалить пользователя по его идентификатору.
     * @param id Идентификатор пользователя для удаления.
     * @throws UserNotFoundException если пользователь с указанным идентификатором не найден.
     */
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteUserById(id);
    }
}
