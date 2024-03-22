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

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new UserNotFoundException(id));
    }

    public User findUserByUserName(String username){
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        return optionalUser.orElseThrow(() -> new UserNotFoundException("Пользователь "+ username +" не найден"));
    }

    public User createUser(User user) {
        if (userRepository.existsUserByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(user);
        }
        user.setId(null);
        user.setActive(true);
        if(user.getRoles() == null)
            user.setRoles(Collections.singleton(Role.USER));
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    public User updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        User deleteUser = userRepository.deleteUserById(id).get();
    }

}
