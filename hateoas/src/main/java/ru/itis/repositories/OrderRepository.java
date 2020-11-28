package ru.itis.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.models.Customer;
import ru.itis.models.Order;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderer(Customer orderer);
    @RestResource(path = "findByStatus", rel = "status")
    Page<Order> findAllByStatus(Order.OrderStatus status, Pageable pageable);
}
