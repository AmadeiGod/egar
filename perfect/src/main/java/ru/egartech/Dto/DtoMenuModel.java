package ru.egartech.Dto;

import lombok.*;



@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class DtoMenuModel {
    private String login;
    private Boolean check = false;
}
