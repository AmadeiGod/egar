package ru.egartech.Dto;

import lombok.Data;

import java.util.Date;
@Data
public class UserDto {
    private String role;
    private Date dateRegistration;
    private Date dateUpdate;
    private int year;
    private String name;
    private String surname;
}
