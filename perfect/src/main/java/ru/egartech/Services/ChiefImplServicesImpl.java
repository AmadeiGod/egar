package ru.egartech.Services;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.Repository.TaskRepository;
import ru.egartech.models.Task;

import java.util.Optional;

@Service
public class ChiefImplServicesImpl implements ChiefImpl {
    @Autowired
    public TaskRepository taskRepository;


    public void chiefCheckTask(long id) {
        if (taskRepository.findById(id).isPresent()) {
            Optional<Task> task = taskRepository.findById(id);
            task.get().setCheckChief(true);
            taskRepository.save(task.get());
        }
    }

    public void chiefCheckTaskSend(long id) {
        if (taskRepository.findById(id).isPresent()) {
            Optional<Task> task = taskRepository.findById(id);
            task.get().setSolve(false);
            taskRepository.save(task.get());
        }

    }

    public void chiefCheckTaskSendAndCom(@Valid long id, Task task) {
        if (taskRepository.findById(id).isPresent()) {
            Optional<Task> task1 = taskRepository.findById(id);
            task1.get().setText(task1.get().getText() + task.getText());
            task1.get().setSolve(false);
            taskRepository.save(task1.get());
        }
    }
}
