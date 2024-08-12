package ru.egartech.Services;

import ru.egartech.models.CalendarPost;
import ru.egartech.models.User;

import java.util.List;

public interface IventServices {
    public void iventAddUserToListForCheckUserAndDeleteUserFromListVisitUser(long id, CalendarPost accept);
    public List<CalendarPost> iventCheckForDeleteTime(User user);
}
