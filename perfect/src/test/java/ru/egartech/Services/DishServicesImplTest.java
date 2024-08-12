package ru.egartech.Services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.egartech.models.*;

@DataJpaTest
@EnableScheduling
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DishServicesImplTest {
    @InjectMocks
    private DishServicesImpl dishServicesImpl;
    Dish dish = new Dish();
    User user = new User();
    CalendarPost calendarPost = new CalendarPost();
    Menu menu = new Menu();

    @Test
    public void mapDishToSendDishForGuest() {
        dish.setName("123");
        dish.setCount(123);

        SendDish sendDish = dishServicesImpl.mapDishToSendDishForGuest(dish, user, calendarPost);
        Assertions.assertEquals(sendDish.getType(), "Гость выбрал");
    }

    @Test
    public void mapDishToSendDishForCalendarPost() {
        dish.setName("123");
        dish.setCount(123);

        SendDish sendDish = dishServicesImpl.mapDishToSendDishForCalendarPost(dish, menu, calendarPost);
        Assertions.assertEquals(sendDish.getType(), "Заготовка");
    }
}
