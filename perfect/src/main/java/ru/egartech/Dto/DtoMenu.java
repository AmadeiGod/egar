package ru.egartech.Dto;

import jakarta.persistence.*;
import lombok.Data;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.Dish;

import java.util.ArrayList;
import java.util.List;
@Data
public class DtoMenu {



    private String name;
    private int count;
    private List<Dish> listDish = new ArrayList<>();

}