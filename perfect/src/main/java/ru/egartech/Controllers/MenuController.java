package ru.egartech.Controllers;

import io.swagger.v3.oas.annotations.Operation;
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
import ru.egartech.Services.DishServices;
import ru.egartech.Services.MenuServices;
import ru.egartech.models.Dish;
import ru.egartech.models.Menu;
import ru.egartech.models.User;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
/**
 * Контроллер меню. Тут происходят основные взаимодейсвтия с блюдами(dish)**/
@Controller
public class MenuController {
    @Autowired
    public DishRepository dishRepository;
    @Autowired
    public MenuServices menuServices;
    @Operation(summary = "Все блюда на складе", description = "СООК")
    @GetMapping("/all-menu")
    public String allMenuPage(Model model){
        model.addAttribute("list", dishRepository.findAll());
        return "cook/all-menu";
    }
    @Operation(summary = "Страница изменения блюд на складе", description = "СООК")
    @GetMapping("/all-menu-update")
    public String allMenuUpdatePage(Model model){
        Menu menu = new Menu();
        menu.setListDish(dishRepository.findAll());
        model.addAttribute("form", menu);
        return "cook/all-menu-update";
    }
    @Operation(summary = "Изменение всего меню склада", description = "СООК")
    @PostMapping("/all-menu-update-post")
    public String allMenuUpdatePost(@ModelAttribute Menu form){
        menuServices.menuUpdate(form);
        return "redirect:/all-menu";
    }
}
