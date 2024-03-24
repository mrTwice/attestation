package ru.yampolskiy.taskclient.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.user.User;

import java.util.List;

@Component
public class UserClientApi{

    @Autowired
    private RestClient restClient;

    @Autowired
    private ObjectMapper objectMapper;


    public CustomResponse<List<User>> getUsers() throws JsonProcessingException {
        String json = restClient
                .get()
                .uri("/users")
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<List<User>>>() {});
    }

    public CustomResponse<User> getUserById(Long id) throws JsonProcessingException {
        String json =  restClient
                .get()
                .uri("/users/" + id)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<User>>() {});
    }

    public CustomResponse<User> findUserByUsername(String username) throws JsonProcessingException {
        String json =   restClient
                .get()
                .uri("/users/find/" + username)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<User>>() {});
    }

    public CustomResponse<User> createUser(User user) throws JsonProcessingException {
        String json = restClient
                .post()
                .uri("/users")
                .body(user)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<User>>() {});
    }

    public CustomResponse<User> updateUser(Long id, User user) throws JsonProcessingException {
        String json = restClient
                .put()
                .uri("/users/" + id)
                .body(user)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<User>>() {});
    }

    public CustomResponse<User> deleteUser(Long id) throws JsonProcessingException {
        String json = restClient
                .delete()
                .uri("/users/" + id)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<User>>() {});
    }

    private <T> CustomResponse<T> deserialization(String jsonObject, TypeReference<CustomResponse<T>> responseType) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(jsonObject);
        return objectMapper.convertValue(jsonNode, responseType);
    }
}

