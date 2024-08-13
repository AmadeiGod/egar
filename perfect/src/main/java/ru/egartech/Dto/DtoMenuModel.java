package ru.egartech.Dto;

import lombok.*;

/**
 * Этот класс нужен для endpoint`a /ivent-check/{id}
 * **/

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class DtoMenuModel {
    private String login;
    private Boolean check = false;
}
