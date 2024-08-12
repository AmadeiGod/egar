package ru.egartech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egartech.Models.Task;
import ru.egartech.Models.User;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserAcceptAndSolve(User user, boolean name);

    List<Task> findByUserSendAndSolveAndCheckChief(User user, boolean name, boolean chief);


}