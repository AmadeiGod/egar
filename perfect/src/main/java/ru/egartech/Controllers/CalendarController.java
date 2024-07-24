package ru.egartech.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.egartech.Dto.DtoMenu;
import ru.egartech.Repository.CalendarPostRepository;

import ru.egartech.Repository.DishRepository;
import ru.egartech.Repository.MenuRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.Services.CalendarPostServices;
import ru.egartech.Services.impl.UserServices;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.Dish;
import ru.egartech.models.Menu;
import ru.egartech.models.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @GetMapping("/calendar")
    public String calendar(Model model){
        List<CalendarPost> list = calendarPostRepository.findAll();
        model.addAttribute("list", list);

        return "calendar/calendar";
    }
    @GetMapping("/edit-calendarPost")
    public String addPostPage(CalendarPost calendarPost,Model model){
        Menu menu = new Menu();
        List<Dish> list = dishRepository.findAll();
        menu.setListDish(list);
        model.addAttribute("form", menu);
        return "calendar/add-calendarPost";
    }
    @PostMapping("/add-calendarPost")
    public String addPost(@ModelAttribute Menu form, CalendarPost calendarPost, Authentication authentication, HttpServletRequest request, Model model) throws ParseException {
        User user = userServices.userAuth(authentication,request);

        try{
            for(int i = 0; i < form.getListDish().size(); i++){
                Dish dish = form.getListDish().get(i);
                Dish dish1 = dishRepository.findByName(dish.getName()).get();
                dish1.setCount(dish1.getCount() - dish.getCount());
                if(dish1.getCount() < 0){
                    return "redirect:lenta";
                }
            }
            for(int i = 0; i < form.getListDish().size(); i++){
                Dish dish = form.getListDish().get(i);
                Dish dish1 = dishRepository.findByName(dish.getName()).get();
                dishRepository.save(dish1);
            }
        }catch (Exception ignored){
        }
        Menu menu = new Menu();
        List<Dish> list = new ArrayList<>();
        for(int i = 0; i < form.getListDish().size();i++){
            Dish dish = form.getListDish().get(i);
            Dish dish1 = dishRepository.save(dish);
            System.out.println(dish1);
            list.add(dish1);
            dishRepository.delete(dish1);
        }
        menu.setListDish(list);

        System.out.println(menu);
        Menu menu1 = menuRepository.save(menu);

        System.out.println(calendarPost);
        calendarPostServices.addCalendarPost(calendarPost,user, menu1);

        return "redirect:lenta";
    }
    @GetMapping("/calendarPostAddUser/{id}")
    public String calendarPostAddUser(@PathVariable("id") long id, Authentication authentication, HttpServletRequest request){
        authentication = (Authentication) request.getUserPrincipal();
        var userDetails = (UserDetails) authentication.getPrincipal();

        Optional<User> user = userRepository.findByPassword(userDetails.getPassword());
        Optional<CalendarPost> calendarPost = calendarPostRepository.findById(id);
        try{
            calendarPostServices.addUserToCalendarPost(user.get(),calendarPost.get());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/menu";
    }
}
