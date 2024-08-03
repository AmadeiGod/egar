package ru.egartech.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarPostRepository extends JpaRepository<CalendarPost, Long> {
    Optional<CalendarPost> findByUserId(Long id);
    Optional<List<CalendarPost>> findAllByUser(User user);
    List<CalendarPost> findAll();

    @Transactional
    @Modifying
    @Query(value = "update CalendarPost set text = %:text% where id = %:id%", nativeQuery = true)
    void updateText(@Param("text") String text, @Param("id") Long id);
}
