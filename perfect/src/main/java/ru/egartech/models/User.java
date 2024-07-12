package ru.egartech.models;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Data
@Table(name = "user_entity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String role;
    private Date dateRegistration;
    private Date dateUpdate;
    private int year;
    private String name;
    private String surname;
}
