package ru.egartech.Dto;


import ru.egartech.models.CalendarPost;
import java.util.ArrayList;
import java.util.List;
import lombok.*;



@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class DtoMenuUser {
    private List<DtoMenuModel> names = new ArrayList<>();
    private List<CalendarPost> cal = new ArrayList<>();
}
