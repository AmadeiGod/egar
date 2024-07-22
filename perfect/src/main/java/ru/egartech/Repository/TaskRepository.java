package ru.egartech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egartech.models.Menu;
import ru.egartech.models.Task;
import ru.egartech.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserAccept(User userAccept);

    List<Task> findByUserAcceptAndSolve(User user, boolean name);
    List<Task> findByUserSendAndSolve(User user, boolean name);
}