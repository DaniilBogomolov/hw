package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OrderDetails {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_hookah",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "hookah_id"))
    private List<Hookah> hookahs;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_tobacco",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "tobacco_id"))
    private List<Tobacco> tobaccos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_bowl",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "bowl_id"))
    private List<Bowl> bowls;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_jar",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "jar_id"))
    private List<ShishaWaterJar> waterJars;
}
