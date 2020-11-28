package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ShishaWaterJar {

    @Id
    @GeneratedValue
    private Long id;

    private String manufacturer;
    private String description;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private GoodsStatus status;
}
