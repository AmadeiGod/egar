package ru.egartech.models;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class TimeCheck {
    public Date dateCreate;
    public Date dateUpdate;
}
