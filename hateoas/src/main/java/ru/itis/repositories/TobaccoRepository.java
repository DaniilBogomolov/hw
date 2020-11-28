package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Tobacco;

public interface TobaccoRepository extends JpaRepository<Tobacco, Long> {
}
