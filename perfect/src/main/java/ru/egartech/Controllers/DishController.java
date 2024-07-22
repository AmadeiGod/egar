package ru.egartech.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.egartech.Repository.DishRepository;
import ru.egartech.models.Dish;

import java.util.List;

@Controller
public class DishController {
    @Autowired
    public DishRepository dishRepository;
    @GetMapping("/cook-menu-addDish")
    public String addDishh(Dish dish){
        return "cook/cook-menu-addDish";
    }
    @PostMapping("/add-dish")
    public String addDish(Dish dish){
        dishRepository.save(dish);
        return "cook/cook-menu-addDish";
    }
    @GetMapping("/cook-menu")
    public String cookMenu(){
        return "cook";
    }
    @GetMapping("/dish")
    public String dish(Model model){
        List<Dish> list = dishRepository.findAll();
        model.addAttribute("list", list);
        return "cook/dish";
    }
}
















