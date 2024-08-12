package ru.egartech.Services.UserServices;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import ru.egartech.Dto.RegDto;
import ru.egartech.Dto.UserDto;
import ru.egartech.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserServicesInterface extends UserDetailsService {
    User save(User user);

    void pageUser(Model model, Authentication authentication, HttpServletRequest request);

    void userSendTask(long id);

    User userRegistration(RegDto registrationDto);

    User userUpdate(User old, User fresh);

    List<UserDto> getListUserDto(List<User> userList);

    User userGetFromAuth(Authentication authentication, HttpServletRequest request);

}
