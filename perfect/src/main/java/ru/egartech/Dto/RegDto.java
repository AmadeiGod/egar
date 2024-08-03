package ru.egartech.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegDto {
    @NotBlank
    @Size(min = 5, max = 100)
    private String login;
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;
    private String email;
}
