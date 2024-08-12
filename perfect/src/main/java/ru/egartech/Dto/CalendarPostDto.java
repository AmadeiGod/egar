package ru.egartech.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class CalendarPostDto {
    private Long id;
    @NotBlank
    @Size(min = 5, max = 100)
    public String text;
    private UserDto userDto;
    private MenuDto menuDto;
    public Date dateCreate;
    public Date dateDelete;
    private String dateDeleteString;
    private List<UserDto> listVisitUserDto = new ArrayList<>();
    private List<UserDto> listForCheckUserDto = new ArrayList<>();
}

