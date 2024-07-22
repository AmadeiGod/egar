package ru.egartech.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

import java.util.Date;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private boolean solve = false;
    private boolean checkChief = false;
    private int scoreTask;
    private String dateDeleteString;
    private Date timeToSolve;
    private Date dateCreate;
    @ManyToOne
    private User userAccept;
    @ManyToOne
    private User userSend;
}
