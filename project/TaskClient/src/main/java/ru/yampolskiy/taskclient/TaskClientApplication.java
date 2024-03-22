package ru.yampolskiy.taskclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableWebSecurity
public class TaskClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskClientApplication.class, args);
    }

}
