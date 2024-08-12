package ru.egartech.Repository;

import ru.egartech.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    Optional<User> findByPassword(String password);
    boolean existsByLogin(String login);
    boolean existsByPassword(String password);

}
