package ru.egartech.Utils;

import org.springframework.stereotype.Service;
import ru.egartech.Dto.*;
import ru.egartech.Models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ForStore - означает, что пользователя мы не указываем
 **/
@Service
public class MappingUtilsDto {
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setName(user.getName());
        userDto.setYear(user.getYear());
        userDto.setSurname(user.getSurname());
        userDto.setRole(user.getRole());
        userDto.setDateRegistration(user.getDateRegistration());
        userDto.setDateUpdate(user.getDateUpdate());
        return userDto;
    }

    public static TaskDto mapToTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setScoreTask(task.getScoreTask());
        taskDto.setText(task.getText());
        taskDto.setUserDtoAccept(mapToUserDto(task.getUserAccept()));
        taskDto.setUserDtoSend(mapToUserDto(task.getUserSend()));
        taskDto.setSolve(false);
        taskDto.setDateCreate(task.getDateCreate());
        taskDto.setDateDeleteString(task.getDateDeleteString());
        taskDto.setTimeToSolve(task.getTimeToSolve());
        taskDto.setCheckChief(false);
        return taskDto;
    }

    public static SendDishDto mapToSendDishDto(SendDish sendDish) {
        SendDishDto sendDishDto = new SendDishDto();
        sendDishDto.setId(sendDish.getId());
        sendDishDto.setUserDto(mapToUserDto(sendDish.getUser()));
        sendDishDto.setCount(sendDish.getCount());
        sendDishDto.setType(sendDish.getType());
        sendDishDto.setName(sendDish.getName());
        sendDishDto.setServiced(sendDish.isServiced());
        return sendDishDto;
    }

    public static SendDishDto mapToSendDishDtoForStore(SendDish sendDish) {
        SendDishDto sendDishDto = new SendDishDto();
        sendDishDto.setId(sendDish.getId());
        sendDishDto.setCount(sendDishDto.getCount());
        sendDishDto.setType(sendDishDto.getType());
        sendDishDto.setName(sendDishDto.getName());
        sendDishDto.setServiced(sendDishDto.isServiced());
        return sendDishDto;
    }

    public static MenuDto mapToMenuDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setListSendDishDto(mapToListSendDishDto(menu.getListSendDish()));
        menuDto.setListDish(menu.getListDish());
        menuDto.setId(menu.getId());
        menuDto.setDateCreate(menu.getDateCreate());
        return menuDto;
    }

    public static MenuDto mapToMenuDtoForStore(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setListSendDishDto(mapToListSendDishDtoForStore(menu.getListSendDish()));
        menuDto.setListDish(menu.getListDish());
        menuDto.setId(menu.getId());
        menuDto.setDateCreate(menu.getDateCreate());
        return menuDto;
    }

    public static CalendarPostDto mapToCalendarPostDto(CalendarPost calendarPost) {
        CalendarPostDto calendarPostDto = new CalendarPostDto();
        calendarPostDto.setId(calendarPost.getId());
        calendarPostDto.setUserDto(mapToUserDto(calendarPost.getUser()));
        calendarPostDto.setDateDelete(calendarPost.getDateDelete());
        calendarPostDto.setDateCreate(calendarPost.getDateCreate());
        calendarPostDto.setDateDeleteString(calendarPost.getDateDeleteString());
        calendarPostDto.setText(calendarPost.getText());
        calendarPostDto.setMenuDto(mapToMenuDto(calendarPost.getMenu()));
        calendarPostDto.setListVisitUserDto(mapListUserDto(calendarPost.getListVisitUser()));
        calendarPostDto.setListForCheckUserDto(mapListUserDto(calendarPost.getListForCheckUser()));
        return calendarPostDto;
    }

    public static CalendarPostDto mapToCalendarPostDtoForStore(CalendarPost calendarPost) {
        CalendarPostDto calendarPostDto = new CalendarPostDto();
        calendarPostDto.setId(calendarPost.getId());
        calendarPostDto.setUserDto(mapToUserDto(calendarPost.getUser()));
        calendarPostDto.setDateDelete(calendarPost.getDateDelete());
        calendarPostDto.setDateCreate(calendarPost.getDateCreate());
        calendarPostDto.setDateDeleteString(calendarPost.getDateDeleteString());
        calendarPostDto.setText(calendarPost.getText());
        calendarPostDto.setMenuDto(mapToMenuDtoForStore(calendarPost.getMenu()));
        calendarPostDto.setListVisitUserDto(mapListUserDto(calendarPost.getListVisitUser()));
        calendarPostDto.setListForCheckUserDto(mapListUserDto(calendarPost.getListForCheckUser()));
        return calendarPostDto;
    }

    public static List<TaskDto> mapToListTaskDto(List<Task> list) {
        List<TaskDto> taskDtoList = new ArrayList<>();
        list.forEach(
                e -> taskDtoList.add(mapToTaskDto(e))
        );
        return taskDtoList;
    }

    public static List<UserDto> mapListUserDto(List<User> userList) {
        List<UserDto> list = new ArrayList<>();
        for (User user : userList) {
            list.add(mapToUserDto(user));
        }
        return list;
    }

    public static List<SendDishDto> mapToListSendDishDto(List<SendDish> list) {
        List<SendDishDto> sendDishDto = new ArrayList<>();
        list.forEach(
                e -> sendDishDto.add(mapToSendDishDto(e))
        );
        return sendDishDto;
    }

    public static List<SendDishDto> mapToListSendDishDtoForStore(List<SendDish> list) {
        List<SendDishDto> sendDishDto = new ArrayList<>();
        list.forEach(
                e -> sendDishDto.add(mapToSendDishDtoForStore(e))
        );
        return sendDishDto;
    }

    public static List<CalendarPostDto> mapToListCalendarPostDto(List<CalendarPost> list) {
        List<CalendarPostDto> calendarPostDto = new ArrayList<>();
        list.forEach(
                e -> calendarPostDto.add(mapToCalendarPostDto(e))
        );
        return calendarPostDto;
    }

    public static List<CalendarPostDto> mapToListCalendarPostDtoForStore(List<CalendarPost> list) {
        List<CalendarPostDto> calendarPostDto = new ArrayList<>();
        list.forEach(
                e -> calendarPostDto.add(mapToCalendarPostDtoForStore(e))
        );
        return calendarPostDto;
    }
}
