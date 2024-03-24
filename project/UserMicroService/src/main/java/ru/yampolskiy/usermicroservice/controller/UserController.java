package ru.yampolskiy.usermicroservice.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yampolskiy.usermicroservice.model.CustomResponse;
import ru.yampolskiy.usermicroservice.model.User;
import ru.yampolskiy.usermicroservice.service.UserService;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        CustomResponse<List<User>> customResponse = new CustomResponse<>(0, users);
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        CustomResponse<User> customResponse = new CustomResponse<>(0, user);
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<CustomResponse<User>> findUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUserName(username);
        CustomResponse<User> customResponse = new CustomResponse<>(0, user);
        return ResponseEntity.ok(customResponse);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<User>> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        CustomResponse<User> customResponse = new CustomResponse<>(0, createdUser);
        return ResponseEntity.ok(customResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        CustomResponse<User> customResponse = new CustomResponse<>(0, updatedUser);
        return ResponseEntity.ok(customResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        CustomResponse<User> customResponse = new CustomResponse<>(0, null);
        return ResponseEntity.ok(customResponse);
    }
}