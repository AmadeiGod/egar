package ru.egartech.Services;

import ru.egartech.Models.User;

import java.text.ParseException;

public interface TaskServices {
    public void createTask(ru.egartech.Models.Task task, User user, String login) throws ParseException;
}
