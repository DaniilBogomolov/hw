package ru.itis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.controllers.OrderController;
import ru.itis.models.Order;
import ru.itis.models.Order.OrderStatus;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrdersRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Order>> {

    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Order> process(EntityModel<Order> model) {
        Order order = model.getContent();
        OrderStatus[] orderStatuses = OrderStatus.values();
        for (int i = 0; i < orderStatuses.length - 1; i++) {
            if (order.getStatus().equals(orderStatuses[i])) {
                model.add(linkTo(methodOn(OrderController.class)
                        .updateStatus(order.getId(), orderStatuses[i + 1]))
                .withRel("updateStatus"));
            }
        }
        return model;
    }
}
