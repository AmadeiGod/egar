package ru.egartech.models;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import ru.egartech.Conventer.CommaDelimitedStringsConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "user_entity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String email;
    private String role;
    private Date dateRegistration;
    private Date dateUpdate;
    private int year;
    private String name;
    private String surname;
    private double taskRaiting;
    private int countTask = 1;
    @OneToMany
    private List<Message> messageList;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CalendarPost> calendarPostList = new ArrayList<>();
    @OneToMany
    private List<Task> taskList;
    @Basic
    @Convert( converter = CommaDelimitedStringsConverter.class )
    private List<String> list = new ArrayList<>();

}
