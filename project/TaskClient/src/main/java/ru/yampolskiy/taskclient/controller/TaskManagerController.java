package ru.yampolskiy.taskclient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.yampolskiy.taskclient.models.CustomResponse;
import ru.yampolskiy.taskclient.models.task.Task;
import ru.yampolskiy.taskclient.models.user.User;
import ru.yampolskiy.taskclient.service.TaskService;
import ru.yampolskiy.taskclient.service.UserService;

import java.util.List;

/**
 * Контроллер для управления задачами пользователя.
 */
@Controller
public class TaskManagerController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    /**
     * Получает все задачи пользователя.
     *
     * @param model          Модель представления.
     * @param authentication Аутентификация пользователя.
     * @return Страница со всеми задачами пользователя.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    @GetMapping("/tasks")
    public String getAllTasks(Model model, Authentication authentication) throws JsonProcessingException {
        User currentUser = getCurrentUser(authentication);
        List<Task> tasks = taskService.findAllUserTasks(currentUser.getId()).getResponseData();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    /**
     * Получает задачу по ее идентификатору.
     *
     * @param id      Идентификатор задачи.
     * @param model   Модель представления.
     * @param session Сессия HTTP.
     * @return Страница с информацией о задаче.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    @GetMapping("/tasks/{id}")
    public String getTaskById(
            @PathVariable Long id,
            Model model, HttpSession session) throws JsonProcessingException {
        CustomResponse<Task> customResponse = taskService.findTaskById(id);
        return handleResponse(customResponse, model, "task", session);
    }

    /**
     * Возвращает форму для создания новой задачи.
     *
     * @param model Модель представления.
     * @return Форма создания новой задачи.
     */
    @GetMapping("/tasks/new")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "createTask";
    }

    /**
     * Создает новую задачу.
     *
     * @param task           Новая задача для создания.
     * @param session        Сессия HTTP.
     * @param authentication Аутентификация пользователя.
     * @return Перенаправление на страницу с задачами после создания.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    @PostMapping("/tasks")
    public String createTask(
            @ModelAttribute Task task,
            HttpSession session,
            Authentication authentication) throws JsonProcessingException {
        User currentUser = getCurrentUser(authentication);
        task.setOwnerId(currentUser.getId());
        CustomResponse<Task> customResponse = taskService.createNewTask(task);
        return handleResponse(customResponse, session, "redirect:/tasks");
    }

    /**
     * Возвращает форму для редактирования задачи.
     *
     * @param id      Идентификатор задачи.
     * @param model   Модель представления.
     * @param session Сессия HTTP.
     * @return Форма редактирования задачи.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    @GetMapping("/tasks/{id}/edit")
    public String editTaskForm(
            @PathVariable Long id,
            Model model, HttpSession session) throws JsonProcessingException {
        CustomResponse<Task> customResponse = taskService.findTaskById(id);
        return handleResponse(customResponse, model, "editTask", session);
    }

    /**
     * Обновляет задачу.
     *
     * @param id      Идентификатор задачи.
     * @param task    Данные для обновления задачи.
     * @param session Сессия HTTP.
     * @return Перенаправление на страницу с задачами после обновления.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    @PostMapping("/tasks/{id}/edit")
    public String updateTask(
            @PathVariable Long id,
            @ModelAttribute Task task, HttpSession session) throws JsonProcessingException {
        CustomResponse<Task> customResponse = taskService.updateTask(id, task);
        return handleResponse(customResponse, session, "redirect:/tasks");
    }

    /**
     * Удаляет задачу.
     *
     * @param id      Идентификатор задачи.
     * @param session Сессия HTTP.
     * @return Перенаправление на страницу с задачами после удаления.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id, HttpSession session) throws JsonProcessingException {
        CustomResponse<Task> customResponse = taskService.deleteTask(id);
        return handleResponse(customResponse, session, "redirect:/tasks");
    }

    /**
     * Получает текущего пользователя.
     *
     * @param authentication Аутентификация пользователя.
     * @return Текущий пользователь.
     * @throws JsonProcessingException Если возникает ошибка при обработке JSON.
     */
    private User getCurrentUser(Authentication authentication) throws JsonProcessingException {
        return userService.findUserByUserName(authentication.getName()).getResponseData();
    }

    /**
     * Обрабатывает ответ от сервера.
     *
     * @param customResponse Ответ сервера.
     * @param model          Модель представления.
     * @param viewName       Имя представления.
     * @param session        Сессия HTTP.
     * @return Представление для отображения.
     */
    private String handleResponse(
            CustomResponse<?> customResponse, Model model, String viewName, HttpSession session) {
        if (customResponse.getErrorCode() != 0) {
            session.setAttribute("exception", customResponse.getResponseError());
            return "redirect:/exception-data";
        } else {
            model.addAttribute("task", customResponse.getResponseData());
            return viewName;
        }
    }

    /**
     * Обрабатывает ответ от сервера и возвращает перенаправление.
     *
     * @param customResponse Ответ сервера.
     * @param session        Сессия HTTP.
     * @param redirectUrl    URL для перенаправления.
     * @return Перенаправление на
     * указанный URL.
     */
    private String handleResponse(CustomResponse<?> customResponse, HttpSession session, String redirectUrl) {
        if (customResponse.getErrorCode() != 0) {
            session.setAttribute("exception", customResponse.getResponseError());
            return "redirect:/exception-data";
        } else {
            return redirectUrl;
        }
    }
}
