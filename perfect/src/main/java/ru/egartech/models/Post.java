package ru.egartech.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
@Data
public class Post {
    @NotBlank
    @Size(min = 5, max = 100)
    public String text;
    public Date dateCreate;
    public Date dateDelete;

}
