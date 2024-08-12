package ru.egartech.Services;

import jakarta.validation.Valid;
import ru.egartech.models.Task;

public interface ChiefImpl {
    public void chiefCheckTaskSendAndCom(@Valid long id, Task task);
    public void chiefCheckTaskSend(long id);
    public void chiefCheckTask(long id);
}
