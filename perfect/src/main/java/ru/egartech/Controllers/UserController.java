package ru.egartech.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import ru.egartech.Repository.TaskRepository;
import ru.egartech.Services.impl.UserServices;
import ru.egartech.models.Task;
import ru.egartech.models.User;
import ru.egartech.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserServices userServices;
    @Autowired
    public TaskRepository taskRepository;

    @GetMapping("/users")
    public String all_users(Model model) throws InterruptedException {
        List<User> list = userRepository.findAll();
        model.addAttribute("list", list);
        return "user/users";
    }
    @GetMapping("/user")
    public String user( Task task, Model model, Authentication authentication, HttpServletRequest request) throws ClassNotFoundException {
        authentication = (Authentication) request.getUserPrincipal();
        var userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> user = userRepository.findByPassword(userDetails.getPassword());
        model.addAttribute("user", user.get());
        model.addAttribute("task1", taskRepository.findByUserAcceptAndSolve(user.get(),false));
        model.addAttribute("taskSolve", taskRepository.findByUserSendAndSolve(user.get(), true));

        return "user/user";
    }
    @GetMapping("/user-send-task/{id}")
    public String userSendTask(@PathVariable("id") long id){
        Optional<Task> task = taskRepository.findById(id);
        task.get().setSolve(true);
        taskRepository.save(task.get());
        return "redirect:user";
    }
}
