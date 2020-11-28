package ru.itis.services;

import ru.itis.models.Customer;
import ru.itis.models.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderHistory(Customer customer);
    List<Order> getAllActiveOrders(Customer customer);
    Order updateStatus(Long orderId, Order.OrderStatus newStatus);
}
