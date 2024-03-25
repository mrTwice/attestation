package ru.yampolskiy.taskclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yampolskiy.taskclient.clients.TaskClientApi;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.task.Task;

import java.util.List;

/**
 * Сервис для работы с задачами.
 */
@Service
public class TaskService {
    @Autowired
    private TaskClientApi taskClientApi;

    /**
     * Создает новую задачу.
     * @param task Новая задача для создания.
     * @return Ответ сервера с созданной задачей.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<Task> createNewTask(Task task) throws JsonProcessingException {
        return taskClientApi.createTask(task);
    }

    /**
     * Удаляет задачу по ее идентификатору.
     * @param id Идентификатор задачи.
     * @return Ответ сервера после удаления задачи.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<Task>  deleteTask(Long id) throws JsonProcessingException {
        return taskClientApi.deleteTask(id);
    }

    /**
     * Обновляет задачу по ее идентификатору.
     * @param id Идентификатор задачи.
     * @param task Новые данные для задачи.
     * @return Ответ сервера с обновленной задачей.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<Task> updateTask(Long id, Task task) throws JsonProcessingException {
        return taskClientApi.updateTask(id, task);
    }

    /**
     * Находит задачу по ее идентификатору.
     * @param id Идентификатор задачи.
     * @return Ответ сервера с найденной задачей.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<Task> findTaskById(Long id) throws JsonProcessingException {
        return taskClientApi.getTaskById(id);
    }

    /**
     * Находит все задачи пользователя по его идентификатору.
     * @param userId Идентификатор пользователя.
     * @return Ответ сервера со всеми задачами пользователя.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<List<Task>> findAllUserTasks(Long userId) throws JsonProcessingException {
        return taskClientApi.getUserTasks(userId);
    }

    /**
     * Получает все задачи.
     * @return Ответ сервера со всеми задачами.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    public CustomResponse<List<Task>> findAllTask() throws JsonProcessingException {
        return taskClientApi.getAllTasks();
    }

}
