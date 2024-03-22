package ru.yampolskiy.taskmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yampolskiy.taskmicroservice.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findTaskByOwnerId(long id);
    Optional<List<Task>> findAllByOwnerId(long id);
}
