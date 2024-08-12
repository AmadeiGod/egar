package ru.egartech.Controllers.Rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.Dto.CalendarPostDto;
import ru.egartech.Dto.RegDto;
import ru.egartech.Dto.UserDto;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.Repository.DishRepository;
import ru.egartech.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egartech.Services.DishServicesImpl;
import ru.egartech.Services.UserServices.UserServices;
import ru.egartech.Utils.MappingUtilsDto;
import ru.egartech.Models.Dish;
import ru.egartech.Models.User;

import java.util.List;

import static ru.egartech.Utils.MappingUtilsDto.*;

@RequestMapping("/rest")
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserServices userServices;
    @Autowired
    public MappingUtilsDto mappingUtilsDto;
    @Autowired
    public DishRepository dishRepository;
    @Autowired
    public CalendarPostRepository calendarPostRepository;
    @Autowired
    public DishServicesImpl dishServicesImpl;
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> restUsers() {
        return ResponseEntity.ok(userServices.getListUserDto(userRepository.findAll()));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> restUserId(@PathVariable("id") long id) {
        if (userRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(mapToUserDto(userRepository.findById(id).get()));
        } else {
            throw new NullPointerException();
        }
    }

    @RequestMapping(value = "/update/user/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<User> restUpdateUserId(@PathVariable("id") long id, @RequestBody User user) {
        if (userRepository.findById(id).isPresent()) {
            return ResponseEntity.status(201).body(userServices.userUpdate(userRepository.findById(id).get(), user));
        }
        return null;
    }

    @PostMapping("/add-dish")
    public ResponseEntity<Dish> restAddDish( Dish dish) {
        return ResponseEntity.ok(dishServicesImpl.save(dish));
    }

    @GetMapping("/all-menu")
    public ResponseEntity<List<Dish>> restAllMenu() {
        return ResponseEntity.ok(dishRepository.findAll());
    }

    @PostMapping("/registration")
    public ResponseEntity<User> restRegisterUserAccount(RegDto registrationDto) {

        if (userRepository.existsByLogin(registrationDto.getLogin()) || userRepository.existsByPassword(registrationDto.getPassword())) {
            return null;
        }
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(userServices.userRegistration(registrationDto));
    }

    @GetMapping("/calendar")
    public ResponseEntity<List<CalendarPostDto>> restAllCalendar() {
        return ResponseEntity.ok(mapToListCalendarPostDto(calendarPostRepository.findAll()));
    }

    @GetMapping("/calendar/{id}")
    public ResponseEntity<CalendarPostDto> restCalendarId(@PathVariable("id") long id) {
        if (calendarPostRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(mapToCalendarPostDto(calendarPostRepository.findById(id).get()));
        }
        return null;
    }
}
