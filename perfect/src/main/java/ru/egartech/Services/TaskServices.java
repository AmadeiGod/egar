package ru.egartech.Services;

import ru.egartech.models.User;

import java.text.ParseException;

public interface TaskServices {
    public void createTask(ru.egartech.models.Task task, User user, String login) throws ParseException;
}
