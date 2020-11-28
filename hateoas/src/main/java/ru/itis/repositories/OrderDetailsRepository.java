package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
