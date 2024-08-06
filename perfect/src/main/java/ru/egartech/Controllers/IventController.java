package ru.egartech.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egartech.Dto.DtoMenuModel;
import ru.egartech.Dto.DtoMenuUser;
import ru.egartech.Repository.*;
import ru.egartech.Services.CalendarPostServices;
import ru.egartech.Services.impl.UserServices;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.Dish;
import ru.egartech.models.Menu;
import ru.egartech.models.User;

import java.util.*;

@Controller
public class IventController {
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
    @Operation(summary = "Просмотр всех мероприятий", description = "USER")
    @GetMapping("/ivent")
    public String ivent(Model model, Authentication authentication, HttpServletRequest request){
        User user = userServices.userAuth(authentication,request);
        Date date = new Date();
        List<CalendarPost> list = calendarPostRepository.findAll();
        model.addAttribute("list", list);
        return "ivent/ivent";
    }
    @Operation(summary = "Просмотр всех созданных мероприятий", description = "HR or MANAGER")
    @GetMapping("/ivent-check")
    public String ivent_chech( Model model, Authentication authentication, HttpServletRequest request, CalendarPost accept){
        User user = userServices.userAuth(authentication,request);
        Date date = new Date();
        List<CalendarPost> list = calendarPostRepository.findAllByUser(user).get();
        list.forEach(e -> {
            if( e.getDateDelete().getTime() < date.getTime()){
                list.remove(e);
            }
        });
        DtoMenuUser dtoMenuUser = new DtoMenuUser();
        dtoMenuUser.setCal(list);
        model.addAttribute("list", list);

        return "ivent/ivent-check";
    }
    @Operation(summary = "Переход на подробности мероприятия, кто принял участие", description = "HR or MANAGER")
    @GetMapping("/ivent-check/{id}")
    public String ivent_orderp(@PathVariable("id") long id,  @ModelAttribute CalendarPost accept, Model model){
        model.addAttribute("id", id);
        CalendarPost calendarPost = calendarPostRepository.findById(id).get();
        model.addAttribute("calendarPost", calendarPost);
        return "/ivent/sss";
    }
    @Operation(summary = "Отмечаем пользователей, которые есть на мероприятии", description = "MANAGER or HR")
    @Transactional
    @PostMapping("/ivent-check/{id}")
    public String ivent_orderPost(@PathVariable("id") long id,  @ModelAttribute CalendarPost accept){
        int i = 0;
        System.out.println(accept);
        CalendarPost accept1 = calendarPostRepository.findById(id).get();
        while (i < accept.getListVisitUser().size()){
            if(accept.getListVisitUser().get(i).isCheckIvent()){
                accept.getListForCheckUser().add(userRepository.findByLogin(accept.getListVisitUser().get(i).getLogin()).get());
                accept1.getListForCheckUser().add(userRepository.findByLogin(accept.getListVisitUser().get(i).getLogin()).get());
                calendarPostRepository.save(accept1);

            }
            i++;
        }

        for( int p = 0; p < accept1.getListVisitUser().size(); p++){
            for(int j = 0; j < accept1.getListForCheckUser().size(); j++){
                if(accept1.getListVisitUser().get(p) == accept1.getListForCheckUser().get(j)){
                    accept1.getListVisitUser().remove(userRepository.findByLogin(accept1.getListVisitUser().get(p).getLogin()).get());
                    calendarPostRepository.save(accept1);
                }
            }
        }

        return "redirect:/ivent-check";
    }
}
