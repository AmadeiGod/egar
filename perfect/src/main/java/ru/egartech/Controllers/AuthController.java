package ru.egartech.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.egartech.Dto.RegDto;

import ru.egartech.Repository.UserRepository;
import ru.egartech.Services.UserServices.UserServices;

@Controller
public class AuthController {
    ;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserServices userServices;

    @Operation(summary = "Авторизация")
    @GetMapping("/auth/login")
    public String pageAuthentication() {
        return "/auth/login";
    }

    @GetMapping("/registration")
    public String pageRegistration(Model model, RegDto registrationDto) {
        model.addAttribute("user", registrationDto);
        return "auth/registration";
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/registration")
    public String registerUserAccount(@Valid RegDto registrationDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "auth/registration";
        }
        if (userRepository.existsByLogin(registrationDto.getLogin())) {
            return "auth/registration";
        }
        userServices.userRegistration(registrationDto);

        return "auth/login";
    }
}
