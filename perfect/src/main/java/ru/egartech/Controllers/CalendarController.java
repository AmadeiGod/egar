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

import ru.egartech.Dto.CalendarPostDto;
import ru.egartech.Dto.MenuDto;
import ru.egartech.Models.*;
import ru.egartech.Repository.*;

import ru.egartech.Services.CalendarPostServicesImpl;

import java.text.ParseException;
import java.util.*;

import static ru.egartech.Utils.MappingUtils.mapToCalendarPost;
import static ru.egartech.Utils.MappingUtils.mapToMenu;
import static ru.egartech.Utils.MappingUtilsDto.mapToListCalendarPostDtoForStore;

/**
 * URL: /calendar <p>
 * Описание: Страница показывает все мероприятия
 * <p>
 * URL: /edit-calendarPost<p>
 * Описание: Страница для добавления мероприятия
 * <p>
 * URL: /add-calendarPost<p>
 * Описание: Добавление мероприятия
 * <p>
 * URL: /ivent<p>
 * Описание: Все мероприятия, которые создал MANAGER или HR
 * <p>
 * URL: /ivent-check<p>
 * Описание: Показывает все мероприятия. Тут MANAGER или HR может нажать на мероприятие и отметить кто пришел на него
 * <p>
 * URL: /ivent-check/{id}<p>
 * Описание: Страница мероприятия. Показывает кто записался на него
 * <p>
 * URL: /ivent-check/{id}<p>
 * Метод: Post
 * Описание: MANAGER или HR отмечает кто пришел на мероприятие
 * **/
@Controller
public class CalendarController {

    @Autowired
    public CalendarPostRepository calendarPostRepository;
    @Autowired
    public CalendarPostServicesImpl calendarPostImpl;
    @Autowired
    public DishRepository dishRepository;

    @GetMapping("/calendar")
    public String calendar(Model model) {
        model.addAttribute("list", mapToListCalendarPostDtoForStore(calendarPostRepository.findAll()));
        MenuDto menuDto = new MenuDto();
        menuDto.setListDish(calendarPostImpl.dishSetCount0());
        model.addAttribute("form", menuDto);
        return "calendar/calendar";
    }

    @GetMapping("/edit-calendarPost")
    public String addPostPage(CalendarPostDto calendarPostDto, Model model) {
        MenuDto menuDto = new MenuDto();
        List<Dish> list = dishRepository.findAll();
        menuDto.setListDish(list);
        model.addAttribute("form", menuDto);
        return "calendar/add-calendarPost";
    }

    @Operation(summary = "Добавление события")
    @Transactional
    @PostMapping("/add-calendarPost")
    public String addPost(@ModelAttribute MenuDto form,
                          @Valid CalendarPostDto calendarPostDto,
                          Authentication authentication,
                          HttpServletRequest request) throws ParseException {
        calendarPostImpl.addPost(mapToMenu(form), mapToCalendarPost(calendarPostDto), authentication, request);
        return "redirect:calendar";
    }

    @Operation(summary = "Добавление пользователя к событию")
    @Transactional
    @PostMapping("/calendarPostAddUser/{id}")
    public String calendarPostAddUser(@ModelAttribute Menu form,
                                      @PathVariable("id") long id,
                                      Authentication authentication,
                                      HttpServletRequest request) throws ParseException {
        calendarPostImpl.calendarPostAddUser(form, id, authentication, request);
        return "redirect:/lenta";
    }

}
