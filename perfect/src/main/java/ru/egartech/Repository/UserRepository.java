package ru.egartech.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    Optional<User> findByPassword(String password);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);

    /*
    @Transactional
    @Modifying
    @Query(value = "update User set email = %:email% where id = %:id%", nativeQuery = true)
    Optional<User> updateCalendarPost(@Param("calendarPost")CalendarPost calendarPost, @Param("id") Long id);*/

}
