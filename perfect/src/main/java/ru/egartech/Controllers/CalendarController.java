package ru.egartech.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.egartech.Dto.DtoMenu;
import ru.egartech.Repository.CalendarPostRepository;

import ru.egartech.Repository.DishRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.Services.CalendarPostServices;
import ru.egartech.Services.impl.UserServices;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.Dish;
import ru.egartech.models.Menu;
import ru.egartech.models.User;

import java.text.ParseException;
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
    @GetMapping("/calendar")
    public String calendar(Model model){
        List<CalendarPost> list = calendarPostRepository.findAll();
        model.addAttribute("list", list);

        return "calendar/calendar";
    }
    @GetMapping("/edit-calendarPost")
    public String addPost(CalendarPost calendarPost,Model model){

        return "calendar/add-calendarPost";
    }
    @PostMapping("/add-calendarPost")
    public String addPostt(CalendarPost calendarPost, Authentication authentication, HttpServletRequest request, Model model) throws ParseException {
        authentication = (Authentication) request.getUserPrincipal();
        var userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> user = userRepository.findByPassword(userDetails.getPassword());

        calendarPostServices.addCalendarPost(calendarPost,user.get());
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
