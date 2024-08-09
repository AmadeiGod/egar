package ru.egartech.Utils;

import org.springframework.stereotype.Service;
import ru.egartech.Dto.*;
import ru.egartech.models.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingUtils {

    public static CalendarPost mapToCalendarPost(CalendarPostDto calendarPostDto) {
        CalendarPost calendarPost = new CalendarPost();
        calendarPost.setId(calendarPostDto.getId());
        calendarPost.setDateDelete(calendarPostDto.getDateDelete());
        calendarPost.setDateCreate(calendarPostDto.getDateCreate());
        calendarPost.setDateDeleteString(calendarPostDto.getDateDeleteString());
        calendarPost.setText(calendarPostDto.getText());
        calendarPost.setListVisitUser(mapListUser(calendarPostDto.getListVisitUserDto()));
        calendarPost.setListForCheckUser(mapListUser(calendarPostDto.getListForCheckUserDto()));
        return calendarPost;
    }
    public static User mapToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setName(userDto.getName());
        user.setYear(userDto.getYear());
        user.setSurname(userDto.getSurname());
        user.setRole(userDto.getRole());
        user.setDateRegistration(userDto.getDateRegistration());
        user.setDateUpdate(userDto.getDateUpdate());
        return user;
    }

    public static Task mapToTask(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setScoreTask(taskDto.getScoreTask());
        task.setText(taskDto.getText());
        task.setUserAccept(mapToUser(taskDto.getUserDtoAccept()));
        task.setUserSend(mapToUser(taskDto.getUserDtoSend()));
        task.setSolve(false);
        task.setDateCreate(taskDto.getDateCreate());
        task.setDateDeleteString(taskDto.getDateDeleteString());
        task.setTimeToSolve(taskDto.getTimeToSolve());
        task.setCheckChief(false);
        return task;
    }

    public static SendDish mapToSendDish(SendDishDto sendDishDto) {
        SendDish sendDish = new SendDish();
        sendDish.setId(sendDish.getId());
        sendDish.setUser(mapToUser(sendDishDto.getUserDto()));
        sendDish.setCount(sendDishDto.getCount());
        sendDish.setType(sendDishDto.getType());
        sendDish.setName(sendDishDto.getName());
        sendDish.setServiced(sendDishDto.isServiced());
        return sendDish;
    }
    public static Menu mapToMenu(MenuDto menuDto) {
        Menu menu = new Menu();
        menu.setListSendDish(mapToListSendDish(menuDto.getListSendDishDto()));
        menu.setListDish(menuDto.getListDish());
        menu.setId(menuDto.getId());
        menu.setDateCreate(menuDto.getDateCreate());
        return menu;
    }
    public static List<User> mapListUser(List<UserDto> userListDto) {
        List<User> list = new ArrayList<>();
        for (UserDto user : userListDto) {
            list.add(mapToUser(user));
        }
        return list;
    }

    public static List<SendDish> mapToListSendDish(List<SendDishDto> listDto) {
        List<SendDish> sendDish = new ArrayList<>();
        listDto.forEach(
                e -> sendDish.add(mapToSendDish(e))
        );
        return sendDish;
    }
}
