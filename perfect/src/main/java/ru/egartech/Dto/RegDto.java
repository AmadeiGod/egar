package ru.egartech.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;



@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class RegDto {
    @NotBlank
    @Size(min = 5, max = 100, message = "Last name should be from 5 to 100 characters")
    private String login;
    @NotBlank
    @Size(min = 5, max = 100, message = "Last name should be from 5 to 100 characters")
    private String password;
    private String email;
}
