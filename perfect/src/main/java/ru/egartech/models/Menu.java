package ru.egartech.models;

import jakarta.persistence.*;
import lombok.Data;
import ru.egartech.Conventer.CommaDelimitedStringsConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Dish> listDish = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<SendDish> listSendDish = new ArrayList<>();
    private Date dateCreate;
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<User> userList;
}
