package ru.yampolskiy.usermicroservice.exception;

import lombok.Getter;
import lombok.Setter;
import ru.yampolskiy.usermicroservice.model.User;

@Getter
@Setter
public class UserAlreadyExistsException extends RuntimeException {
    private User user;
    private String message;

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(User user) {
        this.message = "Пользователь с именем " + user.getUsername() + " cуществует";
    }
}
