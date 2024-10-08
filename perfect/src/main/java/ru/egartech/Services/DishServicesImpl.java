package ru.egartech.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.egartech.Repository.DishRepository;
import ru.egartech.Models.*;

import java.util.Date;

@Service
public class DishServicesImpl implements DishServices {
    @Autowired
    public DishRepository dishRepository;

    public SendDish mapDishToSendDishForGuest(Dish dish, User user, CalendarPost calendarPost) {
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

    public SendDish mapDishToSendDishForCalendarPost(Dish dish, Menu menu, CalendarPost calendarPost) {
        SendDish sendDish = new SendDish();
        sendDish.setCount(dish.getCount());
        sendDish.setName(dish.getName());
        sendDish.setMenu(menu);
        sendDish.setType("Заготовка");
        sendDish.setCalendarPost(calendarPost);
        return sendDish;
    }
    @Cacheable("dish")
    public Dish save(Dish dish) {
        Date date = new Date();
        dish.setDateCreate(date);
        dish.setDateUpdate(date);
        dish.setName(dish.getName());
        dish.setCount(dish.getCount());
        return dishRepository.save(dish);
    }

}
