package ru.egartech.Services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.Repository.TaskRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.models.Task;
import ru.egartech.models.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Service
public class TaskServices {
    @Autowired
    public TaskRepository taskRepository;
    @Autowired
    public UserRepository userRepository;

    public void createTask(Task task, User user, String login) throws ParseException {
        Task task1 = new Task();
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (userRepository.findByLogin(login).isPresent()) {
            task1.setText(task.getText());
            task1.setDateCreate(date);
            task1.setDateDeleteString(task.getDateDeleteString());
            task1.setTimeToSolve(formatter.parse(task.getDateDeleteString()));
            task1.setScoreTask(task.getScoreTask());
            task1.setUserSend(user);
            task1.setUserAccept(userRepository.findByLogin(login).get());
            taskRepository.save(task1);
        }

    }
}
