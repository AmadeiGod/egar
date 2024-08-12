package ru.egartech.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class SendDish extends TimeCheck{
    @NotBlank
    @Size(max = 100)
    public String name;
    @Min(value = 0)
    public int count;
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Menu menu;
    private String type;
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private User user;
    private boolean serviced;
    @ManyToOne(fetch = FetchType.LAZY)
    private CalendarPost calendarPost;
}

