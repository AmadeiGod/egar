package ru.egartech.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
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
