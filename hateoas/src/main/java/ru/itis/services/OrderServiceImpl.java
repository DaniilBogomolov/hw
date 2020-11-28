package ru.itis.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.models.Customer;
import ru.itis.models.Order;
import ru.itis.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Override
    public List<Order> getOrderHistory(Customer customer) {
        return orderRepository.findAllByOrderer(customer);
    }

    @Override
    public List<Order> getAllActiveOrders(Customer customer) {
        return getOrderHistory(customer).stream()
                .filter(order -> !order.getStatus().equals(Order.OrderStatus.DELIVERED))
                .collect(Collectors.toList());
    }

    @Override
    public Order updateStatus(Long orderId, Order.OrderStatus newStatus) {
        Optional<Order> orderCandidate = orderRepository.findById(orderId);
        if (orderCandidate.isPresent()) {
            Order order = orderCandidate.get();
            Order.OrderStatus status = order.getStatus();
            if (newStatus.compareTo(status) == 1) {
                order.setStatus(newStatus);
                orderRepository.save(order);
            } else {
                throw new IllegalStateException("Cannot modify status");
            }
            return order;
        } else {
            throw new IllegalArgumentException("No order found!");
        }
    }
}
