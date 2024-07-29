package ru.egartech.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    @GetMapping("/ivent")
    public String ivent(Model model, Authentication authentication, HttpServletRequest request){
        User user = userServices.userAuth(authentication,request);
        Date date = new Date();
        List<CalendarPost> list = calendarPostRepository.findAll();
        model.addAttribute("list", list);
        return "ivent/ivent";
    }
    @GetMapping("/ivent-check")
    public String ivent_chech(Model model, Authentication authentication, HttpServletRequest request){
        User user = userServices.userAuth(authentication,request);
        Date date = new Date();
        List<CalendarPost> list = calendarPostRepository.findAllByUser(user).get();
        list.forEach(e -> {
            if( e.getDateDelete().getTime() < date.getTime()){
                list.remove(e);
            }
        });
        DtoMenuUser dtoMenuUser = new DtoMenuUser();
        for(int i = 0; i < list.size(); i++){
            for (int k = 0; k < list.get(i).getListVisitUser().size(); k++){
                DtoMenuModel dtoMenuModel = new DtoMenuModel();
                dtoMenuModel.setLogin(list.get(i).getListVisitUser().get(k).getLogin());
                dtoMenuUser.getNames().add(dtoMenuModel);
            }
            dtoMenuUser.getCal().add(list.get(i));
        }
        model.addAttribute("dtoMenuUser", dtoMenuUser );
        return "ivent/ivent-check";
    }
    @PostMapping("/ivent-order")
    public String ivent_order(@ModelAttribute DtoMenuUser dtoMenuUser){
        System.out.println(dtoMenuUser);

        return "redirect:ivent-check";
    }
}
