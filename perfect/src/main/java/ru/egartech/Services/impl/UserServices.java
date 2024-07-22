package ru.egartech.Services.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.User;
import ru.egartech.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices implements UserServicesInterface {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServices() {

    }
    public UserServices(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ login));

        return new CustomUserDetails(user);
    }

    @Override
    public User save(User user) {
        User user1 = new User();
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setLogin(user.getLogin());
        user1.setRole("USER");
        user1.setName(user.getName());
        user1.setSurname(user.getSurname());
        user1.setDateRegistration(user.getDateRegistration());
        user1.setDateUpdate(user.getDateUpdate());
        user1.setYear(user.getYear());


        return userRepository.save(user1);
    }
    public User userAuth(Authentication authentication, HttpServletRequest request){
        authentication = (Authentication) request.getUserPrincipal();
        // Получаем информацию о пользователе
        var userDetails = (UserDetails) authentication.getPrincipal();
        // Используем
        Optional<User> user = userRepository.findByPassword(userDetails.getPassword());
        return user.get();
    }
}
