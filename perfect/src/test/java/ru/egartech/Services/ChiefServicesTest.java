package ru.egartech.Services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.egartech.Repository.TaskRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.Services.UserServices.UserServices;
import ru.egartech.models.Task;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@EnableScheduling
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChiefServicesTest {
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
    @InjectMocks
    public ChiefServices chiefServices;
    Optional<Task> task = Optional.of(new Task());

    @Test
    public void chiefCheckTaskTest() {
        task.get().setText("123");
        task.get().setSolve(false);
        task.get().setCheckChief(false);
        task.get().setId(1L);

        when(taskRepository.findById(task.get().getId())).thenReturn(task);

        chiefServices.chiefCheckTask(task.get().getId());
        if (taskRepository.findById(task.get().getId()).isPresent()) {
            assertThat(taskRepository.findById(task.get().getId()).get().isCheckChief()).isTrue();
        }
    }

    @Test
    public void chiefCheckTaskSendTest() {
        task.get().setText("123");
        task.get().setSolve(false);
        task.get().setCheckChief(false);
        task.get().setId(1L);

        when(taskRepository.findById(task.get().getId())).thenReturn(task);

        chiefServices.chiefCheckTaskSend(task.get().getId());
        if (taskRepository.findById(task.get().getId()).isPresent()) {
            assertThat(taskRepository.findById(task.get().getId()).get().isCheckChief()).isFalse();
        }
    }

    @Test
    public void chiefCheckTaskSendAndComTest() {
        task.get().setText("123");
        task.get().setSolve(false);
        task.get().setCheckChief(false);
        task.get().setId(1L);
        Task task1 = new Task();
        task1.setText("123");

        when(taskRepository.findById(task.get().getId())).thenReturn(task);

        chiefServices.chiefCheckTaskSendAndCom(task.get().getId(), task1);
        if (taskRepository.findById(task.get().getId()).isPresent()) {
            assertThat(taskRepository.findById(task.get().getId()).get().isCheckChief()).isFalse();
            assertThat(taskRepository.findById(task.get().getId()).get().getText()).isEqualTo("123123");
        }
    }
}
