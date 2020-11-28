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
public class Tobacco {

    @Id
    @GeneratedValue
    private Long id;
    private Integer price;
    private String manufacturer;
    private String flavour;
    @Enumerated(EnumType.STRING)
    private GoodsStatus status;
}
