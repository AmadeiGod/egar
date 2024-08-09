package ru.egartech.Dto;


import java.util.Date;
import lombok.*;



@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {
    private long id;
    private String login;
    private String role;
    private Date dateRegistration;
    private Date dateUpdate;
    private int year;
    private String name;
    private String surname;
    private boolean checkIvent = false;
}
