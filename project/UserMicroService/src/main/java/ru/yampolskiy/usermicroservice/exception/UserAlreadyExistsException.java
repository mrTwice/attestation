package ru.yampolskiy.usermicroservice.exception;

import lombok.Getter;
import lombok.Setter;
import ru.yampolskiy.usermicroservice.model.User;

/**
 * Исключение, выбрасываемое при попытке создания пользователя, который уже существует.
 */
@Getter
@Setter
public class UserAlreadyExistsException extends RuntimeException {

    /** Пользователь, который уже существует. */
    private User user;

    /** Сообщение об ошибке. */
    private String message;

    /**
     * Конструктор с сообщением.
     * @param message сообщение об ошибке
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Конструктор с пользователем.
     * @param user пользователь, который уже существует
     */
    public UserAlreadyExistsException(User user) {
        this.user = user;
        this.message = "Пользователь с именем " + user.getUsername() + " уже существует";
    }
}
