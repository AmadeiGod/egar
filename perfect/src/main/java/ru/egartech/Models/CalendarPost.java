package ru.egartech.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@EntityListeners(AuditingEntityListener.class)
public class CalendarPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 5, max = 100, message = "Должен быть больше 5 и меньше 100")
    @LastModifiedBy
    public String text;
    public Date dateCreate;
    public Date dateDelete;
    private String dateDeleteString;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Menu menu;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> listVisitUser = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> listForCheckUser = new ArrayList<>();


}
