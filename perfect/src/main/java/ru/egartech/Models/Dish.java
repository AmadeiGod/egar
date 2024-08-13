package ru.egartech.Models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Dish extends TimeCheck{

    @NotBlank
    @Size(max = 1000)
    private String name;
    private int count;
}
