package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Bowl;

public interface BowlRepository extends JpaRepository<Bowl, Long> {
}
