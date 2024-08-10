package ru.egartech.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egartech.Dto.TaskDto;
import ru.egartech.Repository.TaskRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.Services.ChiefServices;
import ru.egartech.Services.TaskServices;
import ru.egartech.Services.UserServices.UserServices;
import ru.egartech.models.Task;

import java.text.ParseException;

import static ru.egartech.Utils.MappingUtils.mapToTask;
import static ru.egartech.Utils.MappingUtilsDto.mapListUserDto;

/**
 * URL: /giveTask<p>
 * Описание: Страница для добавления задачи сотруднику
 * <p>
 * URL: /giveTaskPost<p>
 * Описание: Добавление задачи сотруднику
 * <p>
 * URL: /chief-check-task/{id}<p>
 * Описание: CHIEF проверил задачу и остался доволен. Задача считается решенной и отправляет в архив
 * <p>
 * URL: /chief-check-task-send/{id}<p>
 * Описание: CHIEF проверил задачу и остался недоволен, поэтому возвращает задачу обратно сотруднику
 * <p>
 * URL: /chief-check-task-send-and-com/{id}<p>
 * Описание: CHIEF проверил задачу и остался недоволен,
 * поэтому оставляет комментарий к задаче и возвращает ее обратно сотруднику
 * **/
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
    @Autowired
    public ChiefServices chiefServices;

    @Operation(summary = "Отправка задачи", description = "CHIEF")
    @PostMapping("/giveTaskPost")
    public String giveTaskToUser(@Valid @ModelAttribute TaskDto task,
                               @Valid String operator, Authentication authentication,
                               HttpServletRequest request) throws ParseException {
        taskServices.createTask(mapToTask(task), userServices.userGetFromAuth(authentication, request), operator);
        return "main/lenta";
    }

    @Operation(summary = "Страница для отправки задач", description = "CHIEF")
    @GetMapping("/giveTask")
    public String pageGiveTask(TaskDto task, Model model) {
        model.addAttribute("list",  mapListUserDto(userRepository.findAll()));
        return "task/givetask";
    }

    @Operation(summary = "Отмечаем задачу решенной", description = "CHIEF")
    @GetMapping("/chief-check-task/{id}")
    public String chiefCheckTask(@PathVariable("id") long id) {
        chiefServices.chiefCheckTask(id);
        return "redirect:user";
    }

    @Operation(summary = "Отправка задачи обратно на доработку", description = "CHIEF")
    @GetMapping("/chief-check-task-send/{id}")
    public String chiefCheckTaskSend(@PathVariable("id") long id) {
        chiefServices.chiefCheckTaskSend(id);
        return "redirect:user";
    }

    @Operation(summary = "Отправка задачи обратно на доработку с комментарием", description = "CHIEF")
    @PostMapping("/chief-check-task-send-and-com/{id}")
    public String chiefCheckTaskSendAndCom(@Valid @PathVariable("id") long id, TaskDto task) {
        chiefServices.chiefCheckTaskSendAndCom(id,mapToTask(task));
        return "redirect:user";
    }
}
