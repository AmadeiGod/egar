package ru.egartech.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egartech.Dto.CalendarPostDto;
import ru.egartech.Dto.DtoMenuUser;
import ru.egartech.Repository.*;
import ru.egartech.Services.IventServicesImpl;
import ru.egartech.Services.UserServices.UserServices;
import ru.egartech.Models.CalendarPost;

import java.util.*;

import static ru.egartech.Utils.MappingUtils.mapToCalendarPost;
import static ru.egartech.Utils.MappingUtilsDto.*;

/**
 * /ivent - показывает все ивенты
 * * <p>
 * /ivent-check - показывает все ивенты, которые создали вы
 * * <p>
 * /ivent-check/{id} - Когда вы находитесь на эндпоинте  /ivent-check, на каждый ивент можно нажать и перейти
 * на более подробную информацию об ивенте и их участниках. Также там можно отметить кто пришел на мероприятие
 * * <p>
 * /ivent-check/{id}(post) - отметили, кто пришел на ивент
 **/
@Controller
@CrossOrigin
public class IventController {
    @Autowired
    public UserServices userServices;
    @Autowired
    public CalendarPostRepository calendarPostRepository;
    @Autowired
    public IventServicesImpl iventServicesImpl;

    @Operation(summary = "Просмотр всех мероприятий", description = "USER")
    @GetMapping("/ivent")
    public String ivent(Model model) {
        model.addAttribute("list", mapToListCalendarPostDtoForStore(calendarPostRepository.findAll()));
        return "ivent/ivent";
    }

    @Operation(summary = "Просмотр всех созданных мероприятий", description = "HR or MANAGER")
    @GetMapping("/ivent-check")
    public String iventCheck(Model model, Authentication authentication, HttpServletRequest request, CalendarPostDto accept) {
        List<CalendarPost> list = iventServicesImpl.iventCheckForDeleteTime(userServices.userGetFromAuth(authentication, request));
        DtoMenuUser dtoMenuUser = new DtoMenuUser();
        dtoMenuUser.setCal(list);
        model.addAttribute("list", list);

        return "ivent/ivent-check";
    }

    @Operation(summary = "Переход на подробности мероприятия, кто принял участие", description = "HR or MANAGER")
    @GetMapping("/ivent-check/{id}")
    public String iventOrderPage(@PathVariable("id") long id, @ModelAttribute CalendarPostDto calendarPostDto, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("calendarPostDto", mapToCalendarPostDtoForStore(calendarPostRepository.findById(id).get()));
        return "/ivent/ivent-check-send";
    }

    @Operation(summary = "Отмечаем пользователей, которые есть на мероприятии", description = "MANAGER or HR")
    @Transactional
    @PostMapping("/ivent-check/{id}")
    public String iventOrderPost(@PathVariable("id") long id, @ModelAttribute CalendarPostDto calendarPostDto) {
        System.out.println(mapToCalendarPost(calendarPostDto));
        iventServicesImpl.iventAddUserToListForCheckUserAndDeleteUserFromListVisitUser(id, mapToCalendarPost(calendarPostDto));
        return "redirect:/ivent-check";
    }
}
