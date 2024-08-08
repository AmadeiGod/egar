package ru.egartech.Services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.egartech.Repository.TaskRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.Services.UserServices.UserServices;
import ru.egartech.models.Task;
import ru.egartech.models.User;

import java.text.ParseException;
import java.util.Optional;

import static org.mockito.Mockito.when;

@DataJpaTest
@EnableScheduling
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskServicesTest {
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    public TaskServices taskServices;
    @InjectMocks
    public UserServices userServices;
    @Mock
    public UserRepository userRepository;

    @Test
    public void giveTaskToUserTest() throws ParseException {
        Task task = new Task();
        task.setText("123");
        task.setDateDeleteString("2022-10-10");

        User user = new User();
        user.setLogin("321");
        user.setPassword("321");

        String login = "user1";
        Optional<User> user1 = Optional.of(new User());
        user1.get().setLogin("user1");

        when(userRepository.findByLogin(login)).thenReturn(user1);
        if (userRepository.findByLogin(login).isPresent()) {
            taskServices.createTask(task, user, login);
        }
    }
}
