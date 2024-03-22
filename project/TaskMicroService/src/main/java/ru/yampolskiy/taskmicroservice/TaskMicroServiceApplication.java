package ru.yampolskiy.taskmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TaskMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskMicroServiceApplication.class, args);
    }

}
