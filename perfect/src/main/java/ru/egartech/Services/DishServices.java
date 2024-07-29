package ru.egartech.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.Repository.DishRepository;
import ru.egartech.models.*;

@Service
public class DishServices {
    @Autowired
    public DishRepository dishRepository;
    public SendDish map_dish_to_sendDish_for_guest(Dish dish, User user, CalendarPost calendarPost){
        SendDish sendDish = new SendDish();
        sendDish.setUser(user);
        sendDish.setName(dish.getName());
        sendDish.setCount(dish.getCount());
        sendDish.setServiced(false);
        sendDish.setMenu(calendarPost.getMenu());
        sendDish.setType("Гость выбрал");
        sendDish.setCalendarPost(calendarPost);
        return sendDish;
    }
    public SendDish map_dish_to_sendDish_for_calendarPost(Dish dish, Menu menu, CalendarPost calendarPost){
        SendDish sendDish = new SendDish();
        sendDish.setCount(dish.getCount());
        sendDish.setName(dish.getName());
        sendDish.setMenu(menu);
        sendDish.setType("Заготовка");
        sendDish.setCalendarPost(calendarPost);
        return sendDish;
    }
}
