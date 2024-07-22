package ru.egartech.Controllers.Rest;

import ru.egartech.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    public UserRepository userRepository;

}
