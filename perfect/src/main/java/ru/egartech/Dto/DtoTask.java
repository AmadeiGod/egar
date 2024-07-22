package ru.egartech.Dto;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import ru.egartech.models.User;

import java.util.Date;
@Data
public class DtoTask {
    private Long id;
    private String text;
    private boolean solve;
    private boolean checkChief;
    private int scoreTask;
    private String dateDeleteString;
    private Date timeToSolve;
    private Date dateCreate;
    private User userAccept;
    private User userSend;
    private String login;
}
