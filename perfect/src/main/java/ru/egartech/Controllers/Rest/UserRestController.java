package ru.egartech.Controllers.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.Utils.MappingUtils;

import ru.egartech.Repository.UserRepository;

@RestController
public class UserRestController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public MappingUtils mappingUtils;/*
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
    }*/
}
