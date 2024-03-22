package ru.yampolskiy.usermicroservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException {
    private Long id;
    private String message;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id){
        this.id = id;
        this.message = "Пользователь с id = " + id + "  не найден.";
    }
}