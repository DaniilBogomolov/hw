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
public class Hookah {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Bowl bowl;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShishaWaterJar waterJar;

    private Integer price;
    private String manufacturer;
    private String description;
    @Enumerated(EnumType.STRING)
    private GoodsStatus status;
}
