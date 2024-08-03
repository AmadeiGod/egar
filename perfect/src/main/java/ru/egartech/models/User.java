package ru.egartech.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
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
    @NotBlank
    @Size(min = 5, max = 100)
    private String login;
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;
    private String email;
    private String role;
    private Date dateRegistration;
    private Date dateUpdate;
    private int year;
    private String name;
    private String surname;
    private boolean checkIvent = false;

};
