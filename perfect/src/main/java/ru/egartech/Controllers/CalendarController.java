package ru.egartech.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import ru.egartech.Services.UserServices.UserServices;

import java.text.ParseException;
import java.util.*;

@Controller
public class CalendarController {

    @Autowired
    public CalendarPostRepository calendarPostRepository;
    @Autowired
    public CalendarPostServices calendarPostServices;
    @Autowired
    public DishRepository dishRepository;

    @GetMapping("/calendar")
    public String calendar(Model model) {
        model.addAttribute("list", calendarPostRepository.findAll());
        Menu menu = new Menu();
        menu.setListDish(calendarPostServices.dishSetCount0());
        model.addAttribute("form", menu);
        return "calendar/calendar";
    }

    @GetMapping("/edit-calendarPost")
    public String addPostPage(CalendarPost calendarPost, Model model) {
        Menu menu = new Menu();
        List<Dish> list = dishRepository.findAll();
        menu.setListDish(list);
        model.addAttribute("form", menu);
        return "calendar/add-calendarPost";
    }

    @Operation(summary = "Добавление события")
    @Transactional
    @PostMapping("/add-calendarPost")
    public String addPost(@ModelAttribute Menu form,
                          @Valid CalendarPost calendarPost,
                          Authentication authentication,
                          HttpServletRequest request,
                          Model model) {
        calendarPostServices.addPost(form, calendarPost, authentication, request, model);
        return "redirect:calendar";
    }

    @Operation(summary = "Добавление пользователя к событию")
    @Transactional
    @PostMapping("/calendarPostAddUser/{id}")
    public String calendarPostAddUser(@ModelAttribute Menu form,
                                      @PathVariable("id") long id,
                                      Authentication authentication,
                                      HttpServletRequest request) {
        calendarPostServices.calendarPostAddUser(form, id, authentication, request);
        return "redirect:/lenta";
    }

}
