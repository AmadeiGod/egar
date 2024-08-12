package ru.egartech.Dto;

import lombok.*;
import ru.egartech.Models.Dish;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class MenuDto {
    private Long id;
    private List<Dish> listDish = new ArrayList<>();
    private List<SendDishDto> listSendDishDto = new ArrayList<>();
    private Date dateCreate;
    private List<UserDto> userDtoList;
}
