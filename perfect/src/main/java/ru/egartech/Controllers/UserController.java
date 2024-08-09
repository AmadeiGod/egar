package ru.egartech.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ru.egartech.Repository.TaskRepository;
import ru.egartech.Services.UserServices.UserServices;
import ru.egartech.models.Task;
import ru.egartech.Repository.UserRepository;

/**
 * URL: /user<p>
 * Описание: Личная страница
 * <p>
 * URL: /users<p>
 * Описание: Все пользователи
 * <p>
 * URL: /user-send-task/{id}<p>
 * Описание: Отправка задачи CHIEF на проверку
 * <p>
 * URL: /calendarPostAddUser/{id}<p>
 * Описание: Добавления USER на мероприятие
 * **/
@Controller
public class UserController {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserServices userServices;
    @Autowired
    public TaskRepository taskRepository;

    @Operation(summary = "Все пользователи")
    @GetMapping("/users")
    public String pageAllUsers(Model model){
        model.addAttribute("list", userServices.getListUserDto(userRepository.findAll()));
        return "user/users";
    }

    @Operation(summary = "Тут информация для каждого пользователя(индивидуально)")
    @GetMapping("/user")
    public String pageUser(Task task, Model model, Authentication authentication, HttpServletRequest request) throws ClassNotFoundException {
        userServices.pageUser(model, authentication, request);
        return "user/user";
    }

    @Operation(summary = "Отправка задачи обратно", description = "USER")
    @GetMapping("/user-send-task/{id}")
    public String userSendTask(@PathVariable("id") long id) {
        userServices.userSendTask(id);
        return "redirect:user";
    }
}
