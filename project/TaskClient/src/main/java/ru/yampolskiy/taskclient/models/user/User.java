package ru.yampolskiy.taskclient.models.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@JsonSerialize
@JsonDeserialize
public class User {
    private Long id;
    private String username;
    private String email;
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
