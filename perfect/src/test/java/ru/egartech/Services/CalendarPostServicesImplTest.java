package ru.egartech.Services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.Repository.DishRepository;
import ru.egartech.Repository.SendDishRepository;
import ru.egartech.Models.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@MockitoSettings(strictness = Strictness.LENIENT)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class CalendarPostServicesImplTest {
    @Mock
    private CalendarPostRepository calendarPostRepository;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private SendDishRepository sendDishRepository;
    @Mock
    private DishRepository dishRepository;
    @InjectMocks
    private CalendarPostServicesImpl calendarPostImpl;
    CalendarPost calendarPost = new CalendarPost();
    User user = new User();
    Dish dish = new Dish();
    Menu menu = new Menu();

    @Test
    public void addUserToCalendarPostTest() throws Exception {

        Mockito.when(calendarPostRepository.save(calendarPost)).thenReturn(calendarPost);
        calendarPostImpl.addUserToCalendarPost(user, calendarPost);

        Assertions.assertEquals(calendarPost.getListVisitUser().get(0), user);

        calendarPostImpl.addUserToCalendarPost(user, calendarPost);
        Assertions.assertEquals(calendarPost.getListVisitUser().size(), 1);
    }

    @Test
    public void addCalendarPostTest() throws ParseException {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        calendarPost.setText("123");
        calendarPost.setDateDeleteString("2024-08-08");

        Mockito.when(calendarPostRepository.save(any())).thenReturn(calendarPost);

        CalendarPost calendarPost1 = calendarPostImpl.addCalendarPost(calendarPost, user, menu);

        Assertions.assertEquals(calendarPost1.getMenu(), menu);
        Assertions.assertEquals(calendarPost1.getUser(), user);
        Assertions.assertEquals(calendarPost1.getDateDelete(), formatter.parse(calendarPost.getDateDeleteString()));
    }

    @Test
    public void checkMenuForDishAndCountDishTest() throws ParseException {

        dish.setCount(2);
        dish.setName("123");
        menu.getListDish().add(dish);
        dish.setCount(1);
        dish.setName("123");
        menu.getListDish().add(dish);
        dish.setCount(0);
        dish.setName("123");
        menu.getListDish().add(dish);

        Assertions.assertTrue(calendarPostImpl.checkMenuForDishAndCountDish(menu, calendarPost));

        menu.setListDish(new ArrayList<>());

        dish.setCount(3);
        dish.setName("123");
        menu.getListDish().add(dish);
        dish.setCount(4);
        dish.setName("123");
        menu.getListDish().add(dish);
        dish.setCount(5);
        dish.setName("123");
        menu.getListDish().add(dish);

        Assertions.assertFalse(calendarPostImpl.checkMenuForDishAndCountDish(menu, calendarPost));

    }

    @Test
    public void reverseDishAndSaveTest() {

        // dish - это новое количество
        // sendDish - это старое количество
        // sendDish1  - это количество на складе

        Optional<SendDish> sendDish = Optional.of(new SendDish());
        Optional<SendDish> sendDish1 = Optional.of(new SendDish());

        dish.setCount(5);
        sendDish.get().setCount(10);
        sendDish1.get().setCount(20);

        Mockito.when(sendDishRepository.findByNameAndUserAndMenu(dish.getName(), user, calendarPost.getMenu()))
                .thenReturn(sendDish);
        Mockito.when(sendDishRepository.findByNameAndTypeAndCalendarPost(sendDish.get().getName(), "Заготовка", calendarPost))
                .thenReturn(sendDish1);

        calendarPostImpl.reverseDishAndSave(dish, calendarPost, user);

        Assertions.assertEquals(sendDish1.get().getCount(), 25);

        dish.setCount(7);
        sendDish.get().setCount(1);
        sendDish1.get().setCount(5);

        calendarPostImpl.reverseDishAndSave(dish, calendarPost, user);

        Assertions.assertEquals(sendDish1.get().getCount(), -1);
    }

    @Test
    public void checkDishWarehouseTest() {

        Dish dish = new Dish();
        Optional<Dish> dish1 = Optional.of(new Dish());

        dish.setCount(10);
        dish1.get().setCount(20);

        menu.getListDish().add(dish);

        Mockito.when(dishRepository.findByName(dish.getName())).thenReturn(dish1);

        Assertions.assertTrue(calendarPostImpl.checkDishWarehouse(menu));

        menu.setListDish(new ArrayList<>());

        dish.setCount(20);
        dish1.get().setCount(10);

        menu.getListDish().add(dish);

        Assertions.assertFalse(calendarPostImpl.checkDishWarehouse(menu));

    }
}
