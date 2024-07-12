package ru.egartech.Controllers;

import ru.egartech.Services.impl.UserServices;
import ru.egartech.models.Document;
import ru.egartech.models.User;
import ru.egartech.repo.DocumentRepository;
import ru.egartech.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    public DocumentRepository documentRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserServices userServices;
}
