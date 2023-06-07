package ru.job4j.order.v1.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.order.v1.model.status.CustomerStatus;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
@Schema(description = "Пользователь")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "идентификационный номер")
    private int id;
    @Column(name = "customer_name")
    @Schema(description = "Имя пользователя")
    private String name;
    @Column(name = "customer_email")
    @Schema(description = "электронная почта")
    private String email;
    @Column(name = "status")
    @Schema(description = "текущий статус")
    private CustomerStatus status;

    public Customer(String cust1) {
        this.name = cust1;
    }
}