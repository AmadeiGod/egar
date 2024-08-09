package ru.egartech.Services.UserServices;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import ru.egartech.Dto.RegDto;
import ru.egartech.Dto.UserDto;
import ru.egartech.Repository.TaskRepository;
import ru.egartech.models.Task;
import ru.egartech.models.User;
import ru.egartech.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static ru.egartech.Utils.MappingUtilsDto.*;

@Service
public class UserServices implements UserServicesInterface {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private TaskRepository taskRepository;

    public UserServices() {

    }

    public UserServices(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: " + login));

        return new CustomUserDetails(user);
    }

    @Override
    public User save(User user) {
        User user1 = new User();
        Date date = new Date();
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setLogin(user.getLogin());
        user1.setRole("USER");
        user1.setName(user.getName());
        user1.setSurname(user.getSurname());
        user1.setDateRegistration(user.getDateRegistration());
        user1.setDateUpdate(user.getDateUpdate());
        user1.setYear(user.getYear());
        user1.setDateRegistration(date);
        user1.setDateUpdate(date);

        return userRepository.save(user1);
    }

    public User userGetFromAuth(Authentication authentication, HttpServletRequest request) {
        authentication = (Authentication) request.getUserPrincipal();
        // Получаем информацию о пользователе
        var userDetails = (UserDetails) authentication.getPrincipal();
        // Используем
        Optional<User> user = userRepository.findByPassword(userDetails.getPassword());
        return user.get();
    }

    public List<UserDto> getListUserDto(List<User> userList) {
        List<UserDto> list = new ArrayList<>();
        for (User user : userList) {
            list.add(mapToUserDto(user));
        }
        return list;
    }

    public User userUpdate(User old, User fresh) {
        Date date = new Date();
        if (!(fresh.getName() == null)) {
            old.setName(fresh.getName());
        }
        if (!(fresh.getEmail() == null)) {
            old.setEmail(fresh.getEmail());
        }
        if (!(fresh.getSurname() == null)) {
            old.setSurname(fresh.getSurname());
        }

        old.setYear(fresh.getYear());
        old.setDateUpdate(date);
        return userRepository.save(old);
    }

    public User userRegistration(RegDto registrationDto) {
        User user = new User();
        user.setLogin(registrationDto.getLogin());
        user.setPassword(registrationDto.getPassword());
        return save(user);
    }

    public void userSendTask(long id) {
        Optional<Task> task = taskRepository.findById(id);
        task.get().setSolve(true);
        taskRepository.save(task.get());
    }

    public void pageUser(Model model, Authentication authentication, HttpServletRequest request) throws ClassNotFoundException {
        User user = userGetFromAuth(authentication, request);
        model.addAttribute("user", mapToUserDto(user));
        model.addAttribute("taskForUser", mapToListTaskDto(taskRepository.findByUserAcceptAndSolve(user, false)));
        model.addAttribute("taskSolveAndCheckNo",
                mapToListTaskDto(taskRepository.findByUserSendAndSolveAndCheckChief(user, true, false)));
        model.addAttribute("taskSolveAndCheckYes",
                mapToListTaskDto(taskRepository.findByUserSendAndSolveAndCheckChief(user, true, true)));
        List<Task> taskList = taskRepository.findByUserSendAndSolveAndCheckChief(user, true, true);
        int sum = 1;
        for (Task task : taskList) {
            sum += task.getScoreTask();
        }
        int scoreUser;
        if (taskList.size() == 0) {
            scoreUser = 0;
        } else {
            scoreUser = sum / taskList.size() + 1;
        }
        model.addAttribute("scoreUser", scoreUser);

    }
}
