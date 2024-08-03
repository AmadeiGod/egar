package ru.egartech.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.egartech.Services.DishServices;
import ru.egartech.models.*;
import ru.egartech.Repository.*;

import ru.egartech.Services.CalendarPostServices;
import ru.egartech.Services.impl.UserServices;

import java.text.ParseException;
import java.util.*;

@Controller
public class CalendarController {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserServices userServices;
    @Autowired
    public CalendarPostRepository calendarPostRepository;
    @Autowired
    public CalendarPostServices calendarPostServices;
    @Autowired
    public DishRepository dishRepository;
    @Autowired
    public MenuRepository menuRepository;
    @Autowired
    public SendDishRepository sendDishRepository;
    @Autowired
    public DishServices dishServices;
    @GetMapping("/calendar")
    public String calendar( Model model){
        List<CalendarPost> list = calendarPostRepository.findAll();
        model.addAttribute("list", list);
        Menu menu = new Menu();
        List<Dish> listt = dishRepository.findAll();
        listt.forEach(e -> e.setCount(0));
        menu.setListDish(listt);
        model.addAttribute("form", menu);
        return "calendar/calendar";
    }
    @GetMapping("/edit-calendarPost")
    public String addPostPage(CalendarPost calendarPost, Model model){
        Menu menu = new Menu();
        List<Dish> list = dishRepository.findAll();
        menu.setListDish(list);
        model.addAttribute("form", menu);
        return "calendar/add-calendarPost";
    }
    @Transactional
    @PostMapping("/add-calendarPost")
    public String addPost(@ModelAttribute Menu form, @Valid CalendarPost calendarPost, Authentication authentication, HttpServletRequest request, Model model) throws ParseException {

        User user = userServices.userAuth(authentication,request);
        Menu menu = new Menu();
        Date date = new Date();
        menu = menuRepository.save(menu);
        List<SendDish> list = new ArrayList<>();
        try{
            if(calendarPostServices.check_dish_warehouse(form)){
                calendarPost = calendarPostServices.addCalendarPost(calendarPost,user, menu);

                for(int k = 0; k < form.getListDish().size(); k++){
                    Dish dish = form.getListDish().get(k);
                    Dish dish1 = dishRepository.findByName(dish.getName()).get();
                    dish1.setCount(dish1.getCount() - dish.getCount());
                    dishRepository.save(dish1);

                    SendDish sendDish = dishServices.map_dish_to_sendDish_for_calendarPost(dish, menu, calendarPost);
                    list.add(sendDishRepository.save(sendDish));
                }
            }

        }catch (Exception ignored){
            ignored.printStackTrace();
        }
        menu.setListSendDish(list);
        menu.setDateCreate(date);

        menuRepository.save(menu);
        return "redirect:calendar";
    }
    @Transactional
    @PostMapping("/calendarPostAddUser/{id}")
    public String calendarPostAddUser(@ModelAttribute Menu form, @PathVariable("id") long id, Authentication authentication, HttpServletRequest request){

        CalendarPost calendarPost = calendarPostRepository.findById(id).get();
        User user = userServices.userAuth(authentication,request);
        boolean check = false;
        for(int i = 0; i < calendarPost.getListVisitUser().size(); i++){ // проверка, пользователь уже участвует на мероприятии?
            if (calendarPost.getListVisitUser().get(i) == user){
                check = true;
            }
        }

        if(sendDishRepository.findByUserAndMenuAndAndCalendarPost(
                user, calendarPost.getMenu(), calendarPost).size() == 0){ // проверка на то, заказывал ли юзер до этого меню
            try{
                if(calendarPostServices.check_menu_for_dish_and_countDish(form, calendarPost)){

                    for(int i = 0; i < form.getListDish().size();i++){
                        SendDish sendDish = dishServices.map_dish_to_sendDish_for_guest(form.getListDish().get(i), user, calendarPost);
                        sendDishRepository.save(sendDish);

                        SendDish sendDish1 = sendDishRepository.findByNameAndTypeAndMenu(sendDish.getName(), "Заготовка", calendarPost.getMenu()).get();
                        sendDish1.setCount(sendDish1.getCount() - sendDish.getCount());
                        sendDishRepository.save(sendDish1);

                    }
                    calendarPostServices.addUserToCalendarPost(user,calendarPost);
                    calendarPost.getMenu().getUserList().add(user);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            try{
                if(calendarPostServices.check_menu_for_dish_and_countDish(form, calendarPost)) {

                    for (int i = 0; i < form.getListDish().size(); i++) {
                        Dish dish = form.getListDish().get(i);
                        calendarPostServices.reverse_dish_and_save(dish,calendarPost,user);
                    }
                    calendarPostRepository.save(calendarPost);
                    calendarPost.getMenu().getUserList().add(user);

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return "redirect:/lenta";


        }

}
