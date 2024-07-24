package ru.egartech.models;

import jakarta.persistence.*;
import lombok.Data;
import ru.egartech.Conventer.CommaDelimitedStringsConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class CalendarPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String dateDeleteString;
    @OneToMany
    private List<Image> listImages = new ArrayList<>();
    private Date dateCreate;
    private Date dateDelete;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Menu menu;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> listVisitUser = new ArrayList<>();

}
