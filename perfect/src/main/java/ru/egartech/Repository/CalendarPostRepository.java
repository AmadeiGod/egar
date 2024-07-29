package ru.egartech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarPostRepository extends JpaRepository<CalendarPost, Long> {
    Optional<CalendarPost> findByUserId(Long id);
    Optional<List<CalendarPost>> findAllByUser(User user);
}
