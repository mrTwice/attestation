package ru.yampolskiy.taskclient.clients;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.task.Task;

import java.util.List;

@Component
public class TaskClientApi {

    @Autowired
    private RestClient restClient;

    @Autowired
    private ObjectMapper objectMapper;

    public CustomResponse<List<Task>> getUserTasks(Long id) throws JsonProcessingException {

        String json = restClient
                .get()
                .uri("/tasks/user/" + id)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<List<Task>>>() {});
    }

    public CustomResponse<List<Task>> getAllTasks() throws JsonProcessingException {
        String json = restClient
                .get()
                .uri("/tasks")
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<List<Task>>>() {});
    }

    public CustomResponse<Task> getTaskById(Long id) throws JsonProcessingException {
        String json =  restClient
                .get()
                .uri("/tasks/" + id)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<Task>>() {});
    }


    public CustomResponse<Task> createTask(Task task) throws JsonProcessingException {
        String json = restClient
                .post()
                .uri("/tasks")
                .body(task)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<Task>>() {});
    }

    public CustomResponse<Task> updateTask(Long id, Task task) throws JsonProcessingException {
        String json = restClient
                .put()
                .uri("/tasks/" + id)
                .body(task)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<Task>>() {});
    }

    public CustomResponse<Task> deleteTask(Long id) throws JsonProcessingException {
        String json = restClient
                .delete()
                .uri("/tasks/" + id)
                .retrieve()
                .body(String.class);
        return deserialization(json, new TypeReference<CustomResponse<Task>>() {});
    }

    private <T> CustomResponse<T> deserialization(String jsonObject, TypeReference<CustomResponse<T>> responseType) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(jsonObject);
        return objectMapper.convertValue(jsonNode, responseType);
    }
}
