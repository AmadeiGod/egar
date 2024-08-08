package ru.egartech.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.Repository.DishRepository;
import ru.egartech.models.*;

import java.util.Date;
import java.util.List;

@Service
public class DishServices {
    @Autowired
    public DishRepository dishRepository;
    public SendDish mapDishToSendDishForGuest(Dish dish, User user, CalendarPost calendarPost){
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
    public SendDish mapDishToSendDishForCalendarPost(Dish dish, Menu menu, CalendarPost calendarPost){
        SendDish sendDish = new SendDish();
        sendDish.setCount(dish.getCount());
        sendDish.setName(dish.getName());
        sendDish.setMenu(menu);
        sendDish.setType("Заготовка");
        sendDish.setCalendarPost(calendarPost);
        return sendDish;
    }
    public Dish save(Dish dish){
        Date date = new Date();
        dish.setDateCreate(date);
        dish.setDateUpdate(date);
        return dishRepository.save(dish);
    }

}
