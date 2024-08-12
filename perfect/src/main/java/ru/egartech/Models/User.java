package ru.egartech.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "user_entity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 5, max = 100, message = "Должен быть больше 5 и меньше 100")
    private String login;
    @NotBlank
    @Size(min = 5, max = 100, message = "Должен быть больше 5 и меньше 100")
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
