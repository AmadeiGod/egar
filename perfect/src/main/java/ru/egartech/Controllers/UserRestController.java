package ru.egartech.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.Dto.UserDto;
import ru.egartech.Services.MappingUtils;
import ru.egartech.Services.impl.UserServices;
import ru.egartech.models.User;
import ru.egartech.repo.DocumentRepository;
import ru.egartech.repo.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static ru.egartech.Services.MappingUtils.mapToUserDto;

@RestController
public class UserRestController {
    @Autowired
    public DocumentRepository documentRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserServices userServices;
    @Autowired
    public MappingUtils mappingUtils;
    @GetMapping("/users")
    public List<User> users(){
        return userRepository.findAll();
    }
    @GetMapping("/user")
    public UserDto user(String login){
        try{
            Optional<User> user = userRepository.findByLogin(login);
            return mapToUserDto(user.get());
        }catch (Exception e ){
            e.printStackTrace();
        }

        return null;
    }
    @PostMapping("/user-add")
    public String add_user(User user){
        try {
            userServices.save(user);
        }catch (Exception e){
            return "Error";
        }
        return "ok";
    }
}
