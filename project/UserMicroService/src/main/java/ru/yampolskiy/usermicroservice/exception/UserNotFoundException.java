package ru.yampolskiy.usermicroservice.exception;

import lombok.Getter;
import lombok.Setter;
import ru.yampolskiy.usermicroservice.model.User;

/**
 * Исключение, выбрасываемое при попытке найти пользователя, который не существует.
 */
@Getter
@Setter
public class UserNotFoundException extends RuntimeException {

    /** Идентификатор пользователя, который не найден. */
    private Long id;

    /** Сообщение об ошибке. */
    private String message;

    /**
     * Конструктор с сообщением.
     * @param message сообщение об ошибке
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Конструктор с идентификатором пользователя.
     * @param id идентификатор пользователя
     */
    public UserNotFoundException(Long id){
        this.id = id;
        this.message = "Пользователь с id = " + id + " не найден.";
    }
}
