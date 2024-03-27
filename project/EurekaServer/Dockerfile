FROM maven:3.9.6-amazoncorretto-21 AS build

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем pom.xml и собираем зависимости
COPY pom.xml .
RUN mvn dependency:go-offline

# Копируем исходные файлы и собираем приложение
COPY src ./src
RUN mvn package

# Этап создания образа
FROM openjdk:23-oracle

# Копируем собранный JAR файл из предыдущего этапа
COPY --from=build /app/target/*.jar /app/app.jar

# Устанавливаем рабочую директорию
WORKDIR /app

# Команда для запуска приложения при старте контейнера
CMD ["java", "-jar", "app.jar"]
