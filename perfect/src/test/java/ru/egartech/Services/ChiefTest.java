package ru.egartech.Services;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import ru.egartech.Repository.TaskRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.Services.UserServices.UserServices;
import ru.egartech.models.Task;
import ru.egartech.models.User;

import java.text.ParseException;

@EnableScheduling
@AutoConfigureMockMvc
@SpringBootTest
public class ChiefTest {
    private static final String NAME1 = "testing";
    private static final String NAME2 = "test";
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    public TaskServices taskServices;
    @InjectMocks
    public UserServices userServices;
    @Mock
    public UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void giveTaskToUserTest() throws ParseException {
        Task task = new Task();
        task.setText("123");
        User user = new User();
        user.setLogin("321");
        user.setPassword("321");
        String login = "user1";

        userRepository.save(user);

        taskServices.createTask(task, user, login);
        if (taskRepository.findById(1L).isPresent()) {
            Assertions.assertEquals(taskRepository.findById(1L).get(), task);
        }

    }
}
