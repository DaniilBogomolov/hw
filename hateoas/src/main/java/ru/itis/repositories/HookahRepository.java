package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.models.Hookah;

import java.util.List;

public interface HookahRepository extends JpaRepository<Hookah, Long> {

    @Query("from Hookah hookah join fetch hookah.bowl join fetch hookah.waterJar where hookah.status = ru.itis.models.GoodsStatus.IN_STOCK")
    List<Hookah> findAllInStock();
}
