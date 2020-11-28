package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate orderDate;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer orderer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "order_orderdetails",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "order_details_id"))
    private OrderDetails details;

    public enum OrderStatus {
        ACCEPTED, READY, IN_DELIVERY, DELIVERED
    }
}
