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

/**
 * Клиент API для работы с пользователями.
 */
@Component
public class UserClientApi {

    @Autowired
    private RestClient restClient;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Получает список всех пользователей.
     * @return Ответ сервера с списком пользователей.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<List<User>> getUsers() throws JsonProcessingException {
        String json = restClient
                .get()
                .uri("/users")
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<List<User>>>() {});
    }

    /**
     * Получает пользователя по его идентификатору.
     * @param id Идентификатор пользователя.
     * @return Ответ сервера с найденным пользователем.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<User> getUserById(Long id) throws JsonProcessingException {
        String json = restClient
                .get()
                .uri("/users/" + id)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<User>>() {});
    }

    /**
     * Находит пользователя по его имени пользователя.
     * @param username Имя пользователя.
     * @return Ответ сервера с найденным пользователем.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<User> findUserByUsername(String username) throws JsonProcessingException {
        String json = restClient
                .get()
                .uri("/users/find/" + username)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<User>>() {});
    }

    /**
     * Создает нового пользователя.
     * @param user Новый пользователь для создания.
     * @return Ответ сервера с созданным пользователем.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<User> createUser(User user) throws JsonProcessingException {
        String json = restClient
                .post()
                .uri("/users")
                .body(user)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<User>>() {});
    }

    /**
     * Обновляет данные пользователя.
     * @param id Идентификатор пользователя для обновления.
     * @param user Новые данные пользователя.
     * @return Ответ сервера с обновленным пользователем.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<User> updateUser(Long id, User user) throws JsonProcessingException {
        String json = restClient
                .put()
                .uri("/users/" + id)
                .body(user)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<User>>() {});
    }

    /**
     * Удаляет пользователя по его идентификатору.
     * @param id Идентификатор пользователя для удаления.
     * @return Ответ сервера после удаления пользователя.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<User> deleteUser(Long id) throws JsonProcessingException {
        String json = restClient
                .delete()
                .uri("/users/" + id)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<User>>() {});
    }

    /**
     * Десериализует JSON-строку в объект типа CustomResponse.
     * @param jsonObject JSON-строка.
     * @param responseType Тип, в который нужно десериализовать.
     * @param <T> Тип объекта.
     * @return Объект типа CustomResponse, полученный после десериализации.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    private <T> CustomResponse<T> deserialization(String jsonObject, TypeReference<CustomResponse<T>> responseType) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(jsonObject);
        return objectMapper.convertValue(jsonNode, responseType);
    }
}
