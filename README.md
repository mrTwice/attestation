# О проекте

Проект создан как и итоговая работа для прохождения курса GeekBrains "Разработчик Java. Цифровые профессии". Цель - закрепление пройденного материала. Разрабатывался с оглядкой на микросервисную архитектуру. Реализовывает минимальный функционал менеджера задач.

# Текущие функциональные возможности

  - регистрация пользователя в сервисе
  - авторизация пользователя
  - получение всего списка задач пользователя
  - создание задачи
  - редактирование задачи
  - просмотр задачи
  - удаление задачи
  - логирование всех обращений к основным контроллерам


# Основа для расширения функционала

  - Управление пользователями сервиса
  - Управление правами пользователей


# Используемые Технологии

1. **Spring Boot**: Этот проект использует [Spring Boot](https://spring.io/projects/spring-boot) версии 3.2.3 для создания веб-приложений.

2. **Spring Data JPA**: Зависимость [`spring-boot-starter-data-jpa`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa) используется для работы с базой данных с использованием Spring Data JPA.

3. **Spring Web**: Зависимость [`spring-boot-starter-web`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web) предоставляет функциональность для разработки веб-приложений на базе Spring.

4. **Spring Cloud Netflix Eureka Server**: Зависимость [`spring-cloud-starter-netflix-eureka-server`](https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-netflix-eureka-server) используется для создания сервера реестра Eureka, который обеспечивает инструмент для обнаружения служб в распределенной среде.

5. **Spring Cloud Netflix Eureka Client**: Зависимость [`spring-cloud-starter-netflix-eureka-client`](https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-netflix-eureka-client) используется для регистрации приложения в сервере Eureka, который является инструментом для обнаружения служб в распределенной среде.

6. **Actuator**: Зависимость [`spring-boot-starter-actuator`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator) добавляет возможности мониторинга и управления приложением Spring Boot.

7. **Micrometer и Prometheus**: Используется для сбора метрик. Зависимости [`micrometer-core`](https://mvnrepository.com/artifact/io.micrometer/micrometer-core) и [`micrometer-registry-prometheus`](https://mvnrepository.com/artifact/io.micrometer/micrometer-registry-prometheus) позволяют интегрировать приложение с системами мониторинга Prometheus.

8. **Spring Security**: Зависимость [`spring-boot-starter-security`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security) предоставляет интеграцию с Spring Security, который используется для обеспечения безопасности веб-приложений.

9. **Spring Boot Validation**: Зависимость [`spring-boot-starter-validation`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation) предоставляет инструменты для валидации данных.

10. **Lombok**: Зависимость [`lombok`](https://mvnrepository.com/artifact/org.projectlombok/lombok) является необязательной и используется для уменьшения объема повторяющегося кода за счет автоматической генерации методов доступа.

11. **Spring Boot Test и JUnit**: Для тестирования приложения используются зависимости [`spring-boot-starter-test`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test) и [`junit`](https://mvnrepository.com/artifact/junit/junit), которые содержат библиотеки и инструменты для написания и запуска тестов.

12. **Spring AOP**: Зависимость [`spring-boot-starter-aop`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-aop) позволяет использовать аспектно-ориентированное программирование в приложении Spring.

13. **Spring Integration**: Зависимость [`spring-boot-starter-integration`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-integration) предоставляет интеграцию с Spring Integration, которая используется для построения сложных интеграционных сценариев в приложении.

14. **MySQL Connector/J**: Зависимость [`mysql-connector-j`](https://mvnrepository.com/artifact/mysql/mysql-connector-java) предоставляет драйвер для подключения к базе данных MySQL.

15. **Docker и Docker Compose**: Зависимость [`spring-boot-starter-docker`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-docker) позволяет создавать контейнеры Docker для приложения Spring Boot. Зависимость [`docker-compose`](https://mvnrepository.com/artifact/com.github.spotify/docker-maven-plugin) используется для управления многоконтейнерными Docker-приложениями.

16. **Grafana**: Инструмент [`Grafana`](https://grafana.com/) используется для визуализации данных и мониторинга приложения.

17. **Spring Integration**: Зависимость [`spring-boot-starter-integration`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-integration) предоставляет интеграцию с Spring Integration, которая используется для построения сложных интеграционных сценариев в приложении.

18. **Thymeleaf**: Зависимость [`spring-boot-starter-thymeleaf`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf) используется для работы

19. **СУБД MySQL**: Система управления базами данных [MySQL](https://www.mysql.com) предоставляет функционал для работы с базами данных.

20. **Docker Desktop**: [Docker Desktop](https://www.docker.com/products/docker-desktop/) графический интерфейс для управления контейнерами, изоляцией приложений и ресурсами, включает в себя: Docker Engine, Docker CLI (Command Line Interface), Docker Compose, Docker Kubernetes, Docker App, Docker Dashboard, Docker Machine, Docker Registry.

21. **Apache Maven**: [Apache Maven](https://maven.apache.org/) используется для управления проектом и сборкой.

22. **IntelliJ IDEA**: Интегрированная среда разработки [IntelliJ IDEA](https://www.jetbrains.com/ru-ru/idea/).

# Запуск и сборка проекта

* Разработка велась в IntelliJ IDEA версии Ultimate. Функционал которой позволяет работать с Docker непосредственно из среды разработки. В случае запуска проекта в среде разработки может потребовать запустить контейнеры с базами данных самостоятельно.

* В для работы над проектом , его сборки и деплоя с целью упрощения работы использовались разные переменные среды.
    * для успешной сборки проекта в среде необходимо установить в конфигурации запуска профиль

          SPRING_PROFILES_ACTIVE=dev

    * Во время сборки проекта используется

          SPRING_PROFILES_ACTIVE=prod

* Для запуска контейнерезированной версии приложения, находясь в директории /project, необходимо выполнить команду

      docker-compose up -d

    * возможно потребуется выполнить команду с правами root пользователя

* После успешного запуска страничка проекта станет доступна по адресу http://localhost:8083
