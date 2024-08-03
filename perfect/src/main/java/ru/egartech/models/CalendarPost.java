package ru.egartech.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NonNull;
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
    @NotBlank
    @Size(min = 5, max = 100)
    public String text;
    public Date dateCreate;
    public Date dateDelete;
    private String dateDeleteString;
    @OneToMany
    private List<Image> listImages = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Menu menu;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> listVisitUser = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> listForCheckUser = new ArrayList<>();


}
