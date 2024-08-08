package ru.egartech.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.Repository.DishRepository;
import ru.egartech.Repository.SendDishRepository;
import ru.egartech.Services.UserServices.UserServices;
import ru.egartech.models.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CalendarPostServices {
    @Autowired
    public CalendarPostRepository calendarPostRepository;
    @Autowired
    public UserServices userServices;
    @Autowired
    public SendDishRepository sendDishRepository;
    @Autowired
    public DishRepository dishRepository;
    public CalendarPost addCalendarPost(CalendarPost calendarPost, User user, Menu menu) throws ParseException {
        CalendarPost calendarPost1 = new CalendarPost();
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        calendarPost1.setId(calendarPost.getId());
        calendarPost1.setText(calendarPost.getText());
        calendarPost1.setDateCreate(date);
        calendarPost1.setDateDeleteString(calendarPost.getDateDeleteString());
        calendarPost1.setDateDelete(formatter.parse(calendarPost.getDateDeleteString()));
        calendarPost1.setUser(user);
        calendarPost1.setMenu(menu);

        calendarPostRepository.save(calendarPost1);
        return calendarPost1;
    }
    public void addUserToCalendarPost(User user, CalendarPost calendarPost) throws Exception{
        try {
            if((calendarPost.getListVisitUser().contains(user))) {
                calendarPost.getListVisitUser().remove(user);
                calendarPost.getListVisitUser().add(user);
                calendarPostRepository.save(calendarPost);

            }else{
                calendarPost.getListVisitUser().add(user);
                calendarPostRepository.save(calendarPost);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }

    }
    public boolean check_menu_for_dish_and_countDish(Menu form, CalendarPost calendarPost){
        for(int i = 0; i < form.getListDish().size();i++){
            Dish dish = form.getListDish().get(i);
            if(dish.getCount() > 3){  // максимум у человека может быть 3 блюда одной позиции
                return false;
            }

        }
        return true;
    }
    public void reverse_dish_and_save(Dish dish, CalendarPost calendarPost, User user){
        SendDish sendDish = sendDishRepository.findByNameAndUserAndMenu(dish.getName(), user, calendarPost.getMenu()).get();
        SendDish sendDish1 = sendDishRepository.findByNameAndTypeAndCalendarPost(
                sendDish.getName(), "Заготовка", calendarPost).get();

        int g = sendDish.getCount();
        sendDish.setCount(dish.getCount());
        sendDishRepository.save(sendDish);
        sendDish1.setCount(sendDish1.getCount() + g - dish.getCount());
        sendDishRepository.save(sendDish1);
    }
    public boolean check_dish_warehouse(Menu form){
        for(int i = 0; i < form.getListDish().size(); i++){
            Dish dish = form.getListDish().get(i);
            Dish dish1 = dishRepository.findByName(dish.getName()).get();
            if(dish1.getCount() - dish.getCount() < 0){
                return false;
            }
        }
        return true;
    }

}
