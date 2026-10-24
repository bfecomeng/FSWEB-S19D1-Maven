package com.workintech.s18d2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vegetable", schema = "fsweb")
public class Vegetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "name boş olamaz")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "price boş olamaz")
    @PositiveOrZero(message = "price negatif olamaz")
    @Column(name = "price", nullable = false)
    private Double price;

    // Lombok primitive boolean için isGrownOnTree() / setGrownOnTree() üretir
    @Column(name = "is_grown_on_tree", nullable = false)
    private boolean isGrownOnTree;
}
