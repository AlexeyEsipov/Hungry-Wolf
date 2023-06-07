package ru.job4j.dish.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu")
@Schema(description = "Блюдо для заказа")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "идентификационный номер блюда в меню")
    private int id;
    @Column(name = "dish_name", nullable = false, unique = true)
    @Schema(description = "наименование блюда")
    private String name;
    @Column(name = "dish_price")
    @Schema(description = "цена за единицу")
    private double price;
    @Column(name = "status")
    @Schema(description = "доступность блюда")
    private MenuStatus status;
}