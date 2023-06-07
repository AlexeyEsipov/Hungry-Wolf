package ru.job4j.kitchen.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Builder
@Table(name = "kitchen_orders")
@Schema(description = "Заказ")
public class KitchenOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Schema(description = "идентификационный номер заказа")
    private int id;
    @Column(name = "order_id")
    @Schema(description = "заказ")
    private int orderId;
    @ElementCollection
    @CollectionTable(name = "order_products")
    @MapKeyColumn(name = "product_name")
    @Column(name = "quantity")
    @Schema(description = "заказанные блюда")
    private Map<String, Integer> dishes = new HashMap<>();
    @Column(name = "status")
    @Schema(description = "текущий статус заказа")
    private OrderStatus orderStatus;
}
