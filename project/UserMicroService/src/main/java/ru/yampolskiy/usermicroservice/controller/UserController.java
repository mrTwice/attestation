package ru.yampolskiy.usermicroservice.controller;


import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yampolskiy.usermicroservice.model.CustomResponse;
import ru.yampolskiy.usermicroservice.model.User;
import ru.yampolskiy.usermicroservice.service.UserService;

import java.util.List;

/**
 * Контроллер для работы с пользователями.
 */
@Data
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Получение всех пользователей.
     * @return ответ с кодом статуса и списком пользователей
     */
    @GetMapping
    public ResponseEntity<CustomResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        CustomResponse<List<User>> customResponse = new CustomResponse<>(0, users);
        return ResponseEntity.ok(customResponse);
    }

    /**
     * Получение пользователя по его идентификатору.
     * @param id идентификатор пользователя
     * @return ответ с кодом статуса и данными пользователя
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        CustomResponse<User> customResponse = new CustomResponse<>(0, user);
        return ResponseEntity.ok(customResponse);
    }

    /**
     * Поиск пользователя по его имени пользователя.
     * @param username имя пользователя
     * @return ответ с кодом статуса и данными пользователя
     */
    @GetMapping("/find/{username}")
    public ResponseEntity<CustomResponse<User>> findUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUserName(username);
        CustomResponse<User> customResponse = new CustomResponse<>(0, user);
        return ResponseEntity.ok(customResponse);
    }

    /**
     * Создание нового пользователя.
     * @param user данные нового пользователя
     * @return ответ с кодом статуса и данными созданного пользователя
     */
    @PostMapping
    public ResponseEntity<CustomResponse<User>> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        CustomResponse<User> customResponse = new CustomResponse<>(0, createdUser);
        return ResponseEntity.ok(customResponse);
    }

    /**
     * Обновление данных пользователя.
     * @param id идентификатор пользователя
     * @param user новые данные пользователя
     * @return ответ с кодом статуса и данными обновленного пользователя
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        CustomResponse<User> customResponse = new CustomResponse<>(0, updatedUser);
        return ResponseEntity.ok(customResponse);
    }

    /**
     * Удаление пользователя по его идентификатору.
     * @param id идентификатор пользователя
     * @return ответ с кодом статуса
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        CustomResponse<User> customResponse = new CustomResponse<>(0, null);
        return ResponseEntity.ok(customResponse);
    }
}
