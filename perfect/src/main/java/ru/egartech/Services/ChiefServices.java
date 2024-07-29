package ru.egartech.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.Repository.TaskRepository;
import ru.egartech.models.Task;

@Service
public class ChiefServices {
    @Autowired
    public TaskRepository taskRepository;



}
