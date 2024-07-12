package ru.egartech.models;

import ru.egartech.Conventer.CommaDelimitedStringsConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String condition;
    private byte[] doc;
    @ManyToOne
    private User user;
    @Basic
    @Convert( converter = CommaDelimitedStringsConverter.class )
    private List<String> nickNames;

}
