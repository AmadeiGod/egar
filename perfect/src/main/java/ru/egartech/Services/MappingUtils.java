package ru.egartech.Services;

import org.springframework.stereotype.Service;
import ru.egartech.Dto.UserDto;
import ru.egartech.models.User;

@Service
public class MappingUtils {
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setYear(user.getYear());
        userDto.setSurname(userDto.getSurname());
        userDto.setRole(userDto.getRole());
        userDto.setDateRegistration(userDto.getDateRegistration());
        userDto.setDateUpdate(userDto.getDateUpdate());
        return userDto;
    }
}
