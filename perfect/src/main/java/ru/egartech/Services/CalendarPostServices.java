package ru.egartech.Services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.Dish;
import ru.egartech.models.Menu;
import ru.egartech.models.User;

import java.text.ParseException;
import java.util.List;

public interface CalendarPostServices {
    public List<Dish> dishSetCount0();
    public void calendarPostAddUser(Menu form, long id, Authentication authentication, HttpServletRequest request) throws ParseException;
    public void addPost(Menu form, CalendarPost calendarPost, Authentication authentication, HttpServletRequest request) throws ParseException;
    public boolean checkDishWarehouse(Menu form);
    public void reverseDishAndSave(Dish dish, CalendarPost calendarPost, User user);
    public boolean checkMenuForDishAndCountDish(Menu form, CalendarPost calendarPost);
    public void addUserToCalendarPost(User user, CalendarPost calendarPost);
    public CalendarPost addCalendarPost(CalendarPost calendarPost, User user, Menu menu) throws ParseException;
}
