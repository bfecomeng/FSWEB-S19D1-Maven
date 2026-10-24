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
@Table(name = "fruit", schema = "fsweb")
public class Fruit {

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

    @NotNull(message = "fruitType boş olamaz (SWEET veya SOUR)")
    @Enumerated(EnumType.STRING)
    @Column(name = "fruit_type", nullable = false)
    private FruitType fruitType;
}
