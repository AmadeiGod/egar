package ru.egartech.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.egartech.Dto.MenuDto;
import ru.egartech.Repository.DishRepository;
import ru.egartech.Services.MenuServicesImpl;

import static ru.egartech.Utils.MappingUtils.mapToMenu;

/**
 * Контроллер меню. Тут происходят основные взаимодейсвтия с блюдами(dish)**/
@Controller
@CrossOrigin
public class MenuController {
    @Autowired
    public DishRepository dishRepository;
    @Autowired
    public MenuServicesImpl menuServicesImpl;
    @Operation(summary = "Все блюда на складе", description = "СООК")
    @GetMapping("/all-menu")
    public String allMenuPage(Model model){
        model.addAttribute("list", dishRepository.findAll());
        return "cook/all-menu";
    }
    @Operation(summary = "Страница изменения блюд на складе", description = "СООК")
    @GetMapping("/all-menu-update")
    public String allMenuUpdatePage(Model model){
        MenuDto menuDto = new MenuDto();
        menuDto.setListDish(dishRepository.findAll());
        model.addAttribute("form", menuDto);
        return "cook/all-menu-update";
    }
    @Operation(summary = "Изменение всего меню склада", description = "СООК")
    @PostMapping("/all-menu-update-post")
    public String allMenuUpdatePost(@ModelAttribute MenuDto form){
        menuServicesImpl.menuUpdate(mapToMenu(form));
        return "redirect:/all-menu";
    }
}
