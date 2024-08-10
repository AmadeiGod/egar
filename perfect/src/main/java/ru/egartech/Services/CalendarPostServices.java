package ru.egartech.Services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.Repository.DishRepository;
import ru.egartech.Repository.MenuRepository;
import ru.egartech.Repository.SendDishRepository;
import ru.egartech.Services.UserServices.UserServices;
import ru.egartech.models.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    public DishServices dishServices;
    @Autowired
    public CalendarPostServices calendarPostServices;
    @Autowired
    public MenuRepository menuRepository;

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

    public void addUserToCalendarPost(User user, CalendarPost calendarPost) throws Exception {
        try {
            if ((calendarPost.getListVisitUser().contains(user))) {
                calendarPost.getListVisitUser().remove(user);
                calendarPost.getListVisitUser().add(user);
                calendarPostRepository.save(calendarPost);

            } else {
                calendarPost.getListVisitUser().add(user);
                calendarPostRepository.save(calendarPost);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean checkMenuForDishAndCountDish(Menu form, CalendarPost calendarPost) {
        for (int i = 0; i < form.getListDish().size(); i++) {
            Dish dish = form.getListDish().get(i);
            if (dish.getCount() > 3) {  // максимум у человека может быть 3 блюда одной позиции
                return false;
            }

        }
        return true;
    }

    public void reverseDishAndSave(Dish dish, CalendarPost calendarPost, User user) {
        SendDish sendDish = sendDishRepository.findByNameAndUserAndMenu(dish.getName(), user, calendarPost.getMenu()).get();
        SendDish sendDish1 = sendDishRepository.findByNameAndTypeAndCalendarPost(sendDish.getName(), "Заготовка", calendarPost).get();

        int g = sendDish.getCount();
        sendDish.setCount(dish.getCount());
        sendDishRepository.save(sendDish);
        sendDish1.setCount(sendDish1.getCount() + g - dish.getCount());
        sendDishRepository.save(sendDish1);
    }

    public boolean checkDishWarehouse(Menu form) {
        for (int i = 0; i < form.getListDish().size(); i++) {
            Dish dish = form.getListDish().get(i);
            Dish dish1 = dishRepository.findByName(dish.getName()).get();
            if (dish1.getCount() - dish.getCount() < 0) {
                return false;
            }
        }
        return true;
    }

    public void addPost(Menu form, @Valid CalendarPost calendarPost, Authentication authentication, HttpServletRequest request, Model model) throws ParseException {
        User user = userServices.userGetFromAuth(authentication, request);
        Menu menu = new Menu();
        Date date = new Date();
        menu = menuRepository.save(menu);
        List<SendDish> list = new ArrayList<>();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (formatter.parse(calendarPost.getDateDeleteString()).getTime() > date.getTime()) {
            try {
                if (calendarPostServices.checkDishWarehouse(form)) {
                    calendarPost = calendarPostServices.addCalendarPost(calendarPost, user, menu);

                    for (int k = 0; k < form.getListDish().size(); k++) {
                        Dish dish = form.getListDish().get(k);
                        Dish dish1 = dishRepository.findByName(dish.getName()).get();
                        dish1.setCount(dish1.getCount() - dish.getCount());
                        dishRepository.save(dish1);

                        SendDish sendDish = dishServices.mapDishToSendDishForCalendarPost(dish, menu, calendarPost);
                        list.add(sendDishRepository.save(sendDish));
                    }
                }

            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
            menu.setListSendDish(list);
            menu.setDateCreate(date);

            menuRepository.save(menu);
        } else {
            throw new ParseException("Неправильное время", 1);
        }

    }

    public void calendarPostAddUser(Menu form, long id, Authentication authentication, HttpServletRequest request) throws ParseException {
        CalendarPost calendarPost = calendarPostRepository.findById(id).get();
        User user = userServices.userGetFromAuth(authentication, request);
        Date date = new Date();

        boolean checkListVisitUser = true;
        for (int i = 0; i < calendarPost.getListVisitUser().size(); i++) { // правило, чтобы пользователь мог заказать меню только один раз
            if (calendarPost.getListVisitUser().get(i) == user) {
                checkListVisitUser = false;
            }
        }
        boolean checkListForCheckUser = true;
        for (int i = 0; i < calendarPost.getListForCheckUser().size(); i++) { // правило, чтобы пользователь мог заказать меню только один раз
            if (calendarPost.getListForCheckUser().get(i) == user) {
                checkListForCheckUser = false;
            }
        }

        if (checkListForCheckUser) {
            if (checkListVisitUser) {
                try {
                    if (calendarPostServices.checkMenuForDishAndCountDish(form, calendarPost)) {

                        for (int i = 0; i < form.getListDish().size(); i++) {
                            SendDish sendDish = dishServices.mapDishToSendDishForGuest(form.getListDish().get(i), user, calendarPost);
                            sendDishRepository.save(sendDish);
                            if (sendDishRepository.findByNameAndTypeAndMenu(sendDish.getName(), "Заготовка", calendarPost.getMenu()).isPresent()) {
                                SendDish sendDish1 = sendDishRepository.findByNameAndTypeAndMenu(sendDish.getName(), "Заготовка", calendarPost.getMenu()).get();
                                sendDish1.setCount(sendDish1.getCount() - sendDish.getCount());
                                sendDishRepository.save(sendDish1);
                            }
                        }
                        calendarPostServices.addUserToCalendarPost(user, calendarPost);
                        calendarPost.getMenu().getUserList().add(user);
                    } else {
                        throw new ParseException("Максимум блюд = 2 ", 1);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if (calendarPostServices.checkMenuForDishAndCountDish(form, calendarPost)) {

                        for (int i = 0; i < form.getListDish().size(); i++) {
                            Dish dish = form.getListDish().get(i);
                            calendarPostServices.reverseDishAndSave(dish, calendarPost, user);
                        }
                        calendarPostRepository.save(calendarPost);
                        calendarPost.getMenu().getUserList().add(user);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new ParseException("Пользователь уже принял участие", 1);
        }

    }

    public List<Dish> dishSetCount0() {
        List<Dish> list = dishRepository.findAll();
        list.forEach(e -> e.setCount(0));
        return list;
    }
}
