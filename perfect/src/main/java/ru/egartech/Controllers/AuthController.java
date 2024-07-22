package ru.egartech.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.egartech.Dto.RegDto;

import ru.egartech.Repository.UserRepository;
import ru.egartech.Services.impl.UserServices;
import ru.egartech.models.User;

@Controller
public class AuthController {
    ;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserServices userServices;


    @GetMapping("/auth/login")
    public String t(){
        return "/auth/login";
    }
    @GetMapping("/registration")
    public String registration(Model model) {
        RegDto regDto = new RegDto();
        model.addAttribute("user", regDto);
        return "auth/registration";}
    @PostMapping("/registration")
    public String registerUserAccount(RegDto registrationDto) {

        if (userRepository.existsByLogin(registrationDto.getLogin())) {
            System.out.println("Такой логин уже есть");
            return "auth/registration";
        }
        User user = new User();
        user.setLogin(registrationDto.getLogin());
        user.setPassword(registrationDto.getPassword());
        userServices.save(user);

        return "auth/login";
    }
}
