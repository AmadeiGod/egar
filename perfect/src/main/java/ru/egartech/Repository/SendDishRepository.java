package ru.egartech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.Menu;
import ru.egartech.models.SendDish;
import ru.egartech.models.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface SendDishRepository extends JpaRepository<SendDish, Long> {
    Optional<SendDish> findByNameAndTypeAndMenu(String name, String type, Menu menu);
    Optional<SendDish> findByNameAndUserAndMenu(String name, User user, Menu menu);
    Optional<SendDish> findByNameAndType(String name, String type);
    Optional<SendDish> findByNameAndTypeAndCalendarPost(String name, String type, CalendarPost calendarPost);
    List<SendDish> findByUser(User user);
    List<SendDish> findByUserAndMenuAndAndCalendarPost(User user, Menu menu, CalendarPost calendarPost);
}
