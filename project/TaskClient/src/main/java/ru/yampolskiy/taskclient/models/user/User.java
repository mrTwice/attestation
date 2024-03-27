package ru.yampolskiy.taskclient.models.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonSerialize
@JsonDeserialize
public class User {
    private Long id;
    @NotBlank(message = "Логин не может быть пустым")
    @Size(min = 4, max = 15)
    private String username;
    @NotBlank(message = "Адрес электронной почты не может быть пустым")
    @Email
    private String email;
    @NotNull(message = "Пароль не может быть пустым")
    @Size(min = 8)
    private String password;
    private boolean active;
    private Set<Role> roles;

    public User(String username, String email, String password, boolean active) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
    }

    public User(String username, String email, String password, boolean active, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }
}
