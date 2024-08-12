package ru.egartech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egartech.Models.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
