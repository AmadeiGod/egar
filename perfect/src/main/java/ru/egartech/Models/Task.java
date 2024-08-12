package ru.egartech.Models;

import jakarta.persistence.*;

import java.util.Date;

import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    public String text;
    private boolean solve = false;
    private boolean checkChief = false;
    @NotBlank
    private int scoreTask;
    private String dateDeleteString;
    private Date timeToSolve;
    private Date dateCreate;
    @ManyToOne
    private User userAccept;
    @ManyToOne
    private User userSend;
}
