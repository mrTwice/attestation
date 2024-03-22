package ru.yampolskiy.taskclient.config;

import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class TaskClientConfiguration {

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.build();
    }

    @Bean
    public RestClientCustomizer restClientCustomizer() {
        String baseUrl = "http://localhost:8762/api/";
        return (restClientBuilder) -> restClientBuilder
                .requestFactory(new JdkClientHttpRequestFactory())
                .baseUrl(baseUrl);
    }
}
