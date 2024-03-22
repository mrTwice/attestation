package ru.yampolskiy.taskclient.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.yampolskiy.taskclient.models.user.User;


import java.util.List;

@Component
public class UserClientApi{

    @Autowired
    private RestClient restClient;

    public List<User> getUsers() {
        ParameterizedTypeReference<List<User>> userLst =
                new ParameterizedTypeReference<List<User>>() {
                };
        return restClient
                .get()
                .uri("/users")
                .retrieve()
                .body(userLst);
    }

    public User getUserById(Long id) {
        return restClient
                .get()
                .uri("/users/" + id)
                .retrieve()
                .toEntity(User.class).getBody();
    }

    public User findUserByUsername(String username) {
        return restClient
                .get()
                .uri("/users/find/" + username)
                .retrieve()
                .toEntity(User.class).getBody();
    }

    public User createUser(User user) {
        return restClient
                .post()
                .uri("/users")
                .body(user)
                .retrieve()
                .body(User.class);
    }

    public User updateUser(Long id, User user) {
        return restClient
                .put()
                .uri("/users/" + id)
                .body(user)
                .retrieve()
                .body(User.class);
    }

    public void deleteUser(Long id) {
        restClient
                .delete()
                .uri("/users/" + id)
                .retrieve()
                .toBodilessEntity();
    }
}

