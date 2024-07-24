package ru.egartech.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.Repository.DishRepository;
import ru.egartech.Repository.MenuRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.models.Dish;
import ru.egartech.models.Menu;
import ru.egartech.models.User;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class MenuController {
    @Autowired
    public MenuRepository menuRepository;
    @Autowired
    public DishRepository dishRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public CalendarPostRepository calendarPostRepository;

    @PostMapping("/add-menu-user")
    public String addMenuUser(@ModelAttribute Menu form){

        return "main/lenta";
    }
    @GetMapping("/menu-cook")
    public String menuCook(Dish dish){
        return "cook";
    }
    @GetMapping("calendar/menu")
    public String menuCookd(Dish dish, Model model){
        Menu menu = new Menu();
        menu.setListDish(dishRepository.findAll());
        model.addAttribute("form", menu);
        return "calendar/menu";
    }
    @GetMapping("/all-menu")
    public String all_menu(Model model){
        List<Dish> list = dishRepository.findAll();
        model.addAttribute("list", list);
        return "cook/all-menu";
    }
    @GetMapping("/all-menu-update")
    public String all_menuUpdate(Model model){
        Menu menu = new Menu();
        menu.setListDish(dishRepository.findAll());
        model.addAttribute("form", menu);
        return "cook/all-menu-update";
    }
    @PostMapping("/all-menu-update-post")
    public String all_menuupdatepost(@ModelAttribute Menu form){
        List<Dish> list = form.getListDish();
        dishRepository.deleteAll();
        list.stream().forEach(e -> {
            if (e.getCount() != 0){
                dishRepository.save(e);
            }
        });
        return "redirect:/all-menu";
    }
}
