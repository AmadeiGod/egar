package ru.egartech.Dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.Dish;
import ru.egartech.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class DtoMenuUser {
    private List<DtoMenuModel> names = new ArrayList<>();
    private List<CalendarPost> cal = new ArrayList<>();
}
