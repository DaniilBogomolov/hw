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
public class Bowl {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private BowlType type;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private GoodsStatus status;
    private String description;

    public enum BowlType {
        EGYPTIAN, VORTEX, PHUNNEL,
    }
}
