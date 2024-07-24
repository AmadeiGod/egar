package ru.egartech.Dto;

import jakarta.persistence.*;
import lombok.Data;
import ru.egartech.models.Menu;

@Data
@Entity
public class DtoDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int count;
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Menu menu;
    // тут еще могут быть поля по типу: калории, вес, горячее, холодное и т.д.
}

