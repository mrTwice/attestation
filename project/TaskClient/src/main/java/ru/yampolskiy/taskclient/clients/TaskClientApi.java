package ru.yampolskiy.taskclient.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.task.Task;

import java.util.List;

/**
 * Клиент для взаимодействия с API задач.
 */
@AllArgsConstructor
@Component
public class TaskClientApi {

    @Autowired
    private RestClient restClient;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Получает задачи пользователя по его идентификатору.
     * @param id Идентификатор пользователя.
     * @return Ответ сервера с задачами пользователя.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<List<Task>> getUserTasks(Long id) throws JsonProcessingException {
        String json = restClient
                .get()
                .uri("/tasks/user/" + id)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<List<Task>>>() {});
    }

    /**
     * Получает все задачи.
     * @return Ответ сервера со всеми задачами.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<List<Task>> getAllTasks() throws JsonProcessingException {
        String json = restClient
                .get()
                .uri("/tasks")
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<List<Task>>>() {});
    }

    /**
     * Получает задачу по ее идентификатору.
     * @param id Идентификатор задачи.
     * @return Ответ сервера с задачей.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<Task> getTaskById(Long id) throws JsonProcessingException {
        String json =  restClient
                .get()
                .uri("/tasks/" + id)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<Task>>() {});
    }

    /**
     * Создает новую задачу.
     * @param task Задача для создания.
     * @return Ответ сервера с созданной задачей.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<Task> createTask(Task task) throws JsonProcessingException {
        String json = restClient
                .post()
                .uri("/tasks")
                .body(task)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<Task>>() {});
    }

    /**
     * Обновляет задачу по ее идентификатору.
     * @param id Идентификатор задачи.
     * @param task Новые данные для задачи.
     * @return Ответ сервера с обновленной задачей.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<Task> updateTask(Long id, Task task) throws JsonProcessingException {
        String json = restClient
                .put()
                .uri("/tasks/" + id)
                .body(task)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<Task>>() {});
    }

    /**
     * Удаляет задачу по ее идентификатору.
     * @param id Идентификатор задачи.
     * @return Ответ сервера после удаления задачи.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<Task> deleteTask(Long id) throws JsonProcessingException {
        String json = restClient
                .delete()
                .uri("/tasks/" + id)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<Task>>() {});
    }

    private <T> CustomResponse<T> deserialization(
            String jsonObject,
            TypeReference<CustomResponse<T>> responseType) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(jsonObject);
        return objectMapper.convertValue(jsonNode, responseType);
    }
}
