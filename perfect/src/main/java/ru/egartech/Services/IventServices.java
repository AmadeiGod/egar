package ru.egartech.Services;

import ru.egartech.Models.CalendarPost;
import ru.egartech.Models.User;

import java.util.List;

public interface IventServices {
    public void iventAddUserToListForCheckUserAndDeleteUserFromListVisitUser(long id, CalendarPost accept);
    public List<CalendarPost> iventCheckForDeleteTime(User user);
}
