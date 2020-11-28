package ru.itis.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.itis.exceptions.BadArgumentsException;
import ru.itis.exceptions.ResourceNotFoundException;
import ru.itis.models.Order;
import ru.itis.services.OrderService;

@RepositoryRestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PutMapping("/orders/{order_id}/update/{new_status}")
    public ResponseEntity<?> updateStatus(@PathVariable("order_id") Long orderId,
                                   @PathVariable("new_status") Order.OrderStatus status) {
        try {
            Order order = orderService.updateStatus(orderId, status);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            throw new BadArgumentsException(e.getMessage());
        }
    }
}
