package ru.egartech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egartech.Models.CalendarPost;
import ru.egartech.Models.Menu;
import ru.egartech.Models.SendDish;
import ru.egartech.Models.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface SendDishRepository extends JpaRepository<SendDish, Long> {
    Optional<SendDish> findByNameAndTypeAndMenu(String name, String type, Menu menu);

    Optional<SendDish> findByNameAndUserAndMenu(String name, User user, Menu menu);

    Optional<SendDish> findByNameAndTypeAndCalendarPost(String name, String type, CalendarPost calendarPost);
    List<SendDish> findByUserAndMenuAndAndCalendarPost(User user, Menu menu, CalendarPost calendarPost);
}
