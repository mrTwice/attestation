package ru.yampolskiy.taskmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yampolskiy.taskmicroservice.model.Task;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с сущностями задач в базе данных.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Находит задачу по идентификатору владельца.
     * @param id Идентификатор владельца задачи.
     * @return Опциональный объект, содержащий найденную задачу или пустое значение.
     */
    Optional<Task> findTaskByOwnerId(long id);

    /**
     * Находит все задачи по идентификатору владельца.
     * @param id Идентификатор владельца задач.
     * @return Опциональный список задач, принадлежащих указанному владельцу, или пустой список.
     */
    Optional<List<Task>> findAllByOwnerId(long id);
}

