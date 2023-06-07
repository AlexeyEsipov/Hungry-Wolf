package ru.job4j.order.v1.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.job4j.order.v1.model.status.OrderStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Builder
@Table(name = "orders")
@Schema(description = "Заказ")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Schema(description = "идентификационный номер заказа")
    private int id;
    @ManyToOne
    @JoinColumn(name = "customer_db_id")
    @Schema(description = "пользователь")
    private Customer customer;
    @Column(name = "created")
    @Schema(description = "дата создания")
    private Date created;
    @Column(name = "modify")
    @Schema(description = "дата последней модификации")
    private Date modify;
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