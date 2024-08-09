package ru.egartech.Dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.egartech.models.User;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class TaskDto {
    private Long id;
    @NotNull
    private String text;
    private boolean solve = false;
    private boolean checkChief = false;
    @NotNull
    private int scoreTask;
    private String dateDeleteString;
    private Date timeToSolve;
    private Date dateCreate;
    private UserDto userDtoAccept;
    private UserDto userDtoSend;
}
