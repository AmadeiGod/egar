package ru.egartech.Dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.Menu;
import ru.egartech.models.User;
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class SendDishDto {
    private Long id;
    @NotNull
    @Size(max = 100)
    private String name;
    @Min(value = 0)
    private int count;
    private MenuDto menuDto;
    private String type;
    private UserDto userDto;
    private boolean serviced;
    private CalendarPostDto calendarPostDto;
}
