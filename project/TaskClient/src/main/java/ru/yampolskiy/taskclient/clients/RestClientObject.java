package ru.yampolskiy.taskclient.clients;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * Класс RestClientObject представляет объект клиента REST.
 */
@Data
@Component
@Getter
public class RestClientObject {

    /**
     * Базовый URI для обращения к API.
     */
    private static final String BASE_URI = "http://localhost:8762/api/";

    /**
     * RestClient для выполнения HTTP-запросов к API.
     */
    protected RestClient restClient = RestClient.create(BASE_URI);
}
