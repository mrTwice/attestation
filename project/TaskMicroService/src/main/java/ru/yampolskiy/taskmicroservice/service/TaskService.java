package ru.yampolskiy.taskmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yampolskiy.taskmicroservice.exception.TaskIdNotNullException;
import ru.yampolskiy.taskmicroservice.exception.TaskNotFoundException;
import ru.yampolskiy.taskmicroservice.model.Task;
import ru.yampolskiy.taskmicroservice.model.TaskStatus;
import ru.yampolskiy.taskmicroservice.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для управления задачами.
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Получает все задачи.
     * @return Список всех задач.
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Получает все задачи пользователя.
     * @param id Идентификатор пользователя.
     * @return Список задач пользователя.
     */
    public List<Task> getAllUserTask(Long id) {
        return taskRepository.findAllByOwnerId(id).orElse(Collections.emptyList());
    }

    /**
     * Получает задачу по идентификатору.
     * @param id Идентификатор задачи.
     * @return Задача с указанным идентификатором.
     * @throws TaskNotFoundException Если задача с указанным идентификатором не найдена.
     */
    public Task getTaskById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.orElseThrow(() -> new TaskNotFoundException(id));
    }

    /**
     * Создает новую задачу.
     * @param task Новая задача.
     * @return Созданная задача.
     * @throws TaskIdNotNullException Если идентификатор задачи уже задан.
     */
    public Task createTask(Task task) {
        if (task.getId() != null) {
            throw new TaskIdNotNullException(task);
        }
        task.setCreated(LocalDateTime.now());
        task.setStatus(TaskStatus.OPEN);
        task.setLastUpdate(task.getCreated());
        return taskRepository.save(task);
    }

    /**
     * Обновляет существующую задачу.
     * @param id Идентификатор задачи для обновления.
     * @param task Обновленные данные задачи.
     * @return Обновленная задача.
     * @throws TaskNotFoundException Если задача с указанным идентификатором не найдена.
     */
    public Task updateTask(Long id, Task task) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        task.setId(id);
        task.setLastUpdate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    /**
     * Удаляет задачу по идентификатору.
     * @param id Идентификатор задачи для удаления.
     * @throws TaskNotFoundException Если задача с указанным идентификатором не найдена.
     */
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
}
