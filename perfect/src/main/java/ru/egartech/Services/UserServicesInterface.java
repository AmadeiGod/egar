package ru.egartech.Services;

import ru.egartech.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
public interface UserServicesInterface extends UserDetailsService{
        User save(User user);
}
