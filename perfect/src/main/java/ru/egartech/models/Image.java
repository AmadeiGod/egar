package ru.egartech.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String contentType;
    private Long size;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;


}
