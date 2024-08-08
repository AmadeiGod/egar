package ru.egartech.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egartech.Repository.TaskRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.Services.TaskServices;
import ru.egartech.Services.impl.UserServices;
import ru.egartech.models.Task;
import ru.egartech.models.User;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Controller
public class ChiefController {
    @Autowired
    public TaskServices taskServices;
    @Autowired
    public UserServices userServices;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public TaskRepository taskRepository;

    @Operation(summary = "Отправка задачи", description = "CHIEF")
    @PostMapping("/giveTaskPost")
    public String giveTaskToUser(@Valid @ModelAttribute Task task,
                               @Valid String operator, Authentication authentication,
                               HttpServletRequest request) throws ParseException {
        taskServices.createTask(task, userServices.userGetFromAuth(authentication, request), operator);
        return "main/lenta";
    }

    @Operation(summary = "Страница для отправки задач", description = "CHIEF")
    @GetMapping("/giveTask")
    public String pageGiveTask(Task task, Model model) {
        model.addAttribute("list", userRepository.findAll());
        return "task/givetask";
    }

    @Operation(summary = "Отмечаем задачу решенной", description = "CHIEF")
    @GetMapping("/chief-check-task/{id}")
    public String chiefCheckTask(@PathVariable("id") long id) {
        Optional<Task> task = taskRepository.findById(id);
        task.get().setCheckChief(true);
        taskRepository.save(task.get());
        User user = task.get().getUserAccept();

        userRepository.save(user);
        return "redirect:user";
    }

    @Operation(summary = "Отправка задачи обратно на доработку", description = "CHIEF")
    @GetMapping("/chief-check-task-send/{id}")
    public String chiefCheckTaskSend(@PathVariable("id") long id) {
        Optional<Task> task = taskRepository.findById(id);
        task.get().setSolve(false);
        taskRepository.save(task.get());
        return "redirect:user";
    }

    @Operation(summary = "Отправка задачи обратно на доработку с комментарием", description = "CHIEF")
    @PostMapping("/chief-check-task-send-and-com/{id}")
    public String chiefCheckTaskSendAndCom(@Valid @PathVariable("id") long id, Task task) {
        Optional<Task> task1 = taskRepository.findById(id);
        task1.get().setText(task1.get().getText() + task.getText());
        task1.get().setSolve(false);
        taskRepository.save(task1.get());
        return "redirect:user";
    }
}
