package ru.egartech.models;

import jakarta.persistence.*;
import lombok.Data;
import ru.egartech.Conventer.CommaDelimitedStringsConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private CalendarPost calendarPost;
    @OneToMany
    private List<Dish> listDish = new ArrayList<>();

}
