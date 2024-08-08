package ru.egartech.Controllers.Rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.Dto.RegDto;
import ru.egartech.Dto.UserDto;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.Repository.DishRepository;
import ru.egartech.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egartech.Services.UserServices.UserServices;
import ru.egartech.Utils.MappingUtils;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.Dish;
import ru.egartech.models.User;

import java.util.List;

import static ru.egartech.Utils.MappingUtils.mapToUserDto;

@RequestMapping("/rest")
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserServices userServices;
    @Autowired
    public MappingUtils mappingUtils;
    @Autowired
    public DishRepository dishRepository;
    @Autowired
    public CalendarPostRepository calendarPostRepository;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> restUsers() {
        return ResponseEntity.ok(userServices.getListUserDto(userRepository.findAll()));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> restUserId(@PathVariable("id") long id) {
        if (userRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(mapToUserDto(userRepository.findById(id).get()));
        }
        return null;
    }

    @RequestMapping(value = "/update/user/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<User> restUpdateUserId(@PathVariable("id") long id, @RequestBody User user) {
        if (userRepository.findById(id).isPresent()) {
            return ResponseEntity.status(201).body(userServices.userUpdate(userRepository.findById(id).get(), user));
        }
        return null;
    }

    @PostMapping("/add-dish")
    public ResponseEntity<Dish> restAddDish(@Valid Dish dish) {
        return ResponseEntity.ok(dishRepository.save(dish));
    }

    @GetMapping("/all-menu")
    public ResponseEntity<List<Dish>> restAllMenu() {
        return ResponseEntity.ok(dishRepository.findAll());
    }

    @PostMapping("/registration")
    public ResponseEntity<User> restRegisterUserAccount(@Valid RegDto registrationDto) {

        if (userRepository.existsByLogin(registrationDto.getLogin()) || userRepository.existsByPassword(registrationDto.getPassword())) {
            return null;
        }
        return ResponseEntity.ok(userServices.userRegistration(registrationDto));
    }

    @GetMapping("/calendar")
    public ResponseEntity<List<CalendarPost>> restAllCalendar() {
        return ResponseEntity.ok(calendarPostRepository.findAll());
    }

    @GetMapping("/calendar/{id}")
    public ResponseEntity<CalendarPost> restCalendarId(@PathVariable("id") long id) {
        if (calendarPostRepository.findById(id).isPresent()) {
            ResponseEntity.ok(calendarPostRepository.findById(id).get());
        }
        return null;
    }
}
