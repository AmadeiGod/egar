package ru.egartech.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class TimeCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Date dateCreate;
    public Date dateUpdate;
}
