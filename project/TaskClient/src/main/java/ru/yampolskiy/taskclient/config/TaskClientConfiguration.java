package ru.yampolskiy.taskclient.config;

import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

/**
 * Конфигурация клиента TaskClient.
 */
@Configuration
public class TaskClientConfiguration {

    /**
     * Создает экземпляр RestClient.
     * @param builder Builder для создания RestClient.
     * @return Экземпляр RestClient.
     */
    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.build();
    }

    /**
     * Создает экземпляр RestClientCustomizer для настройки RestClient.
     * @return Экземпляр RestClientCustomizer.
     */
    @Bean
    public RestClientCustomizer restClientCustomizer() {
        // Базовый URL для всех запросов
        String baseUrl = "http://localhost:8762/api/";
        // Возвращает функцию для настройки RestClientBuilder
        return (restClientBuilder) -> restClientBuilder
                // Использует JdkClientHttpRequestFactory для создания HTTP-запросов
                .requestFactory(new JdkClientHttpRequestFactory())
                // Устанавливает базовый URL для всех запросов
                .baseUrl(baseUrl);
    }
}
