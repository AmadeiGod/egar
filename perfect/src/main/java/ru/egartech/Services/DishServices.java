package ru.egartech.Services;

import ru.egartech.Models.*;

public interface DishServices {
    public Dish save(Dish dish);
    public SendDish mapDishToSendDishForCalendarPost(Dish dish, Menu menu, CalendarPost calendarPost);
    public SendDish mapDishToSendDishForGuest(Dish dish, User user, CalendarPost calendarPost);
}
