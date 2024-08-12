package ru.egartech.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
