package ru.yampolskiy.taskclient.clients;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Data
@Component
@Getter
public class RestClientObject {
    private static final String BASE_URI = "http://localhost:8762/api/";

    protected RestClient restClient = RestClient.create(BASE_URI);
}