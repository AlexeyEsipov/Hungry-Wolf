package ru.job4j.order.v1.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.job4j.order.v1.model.status.CardName;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Table(name = "cards")
@Schema(description = "Карта лояльности")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    @Schema(description = "идентификационный номер карты")
    private int id;
    @ManyToOne
    @JoinColumn (name = "customer_id")
    @Schema(description = "идентификационный номер пользователя")
    private Customer customerId;
    @Column(name = "card_name")
    @Schema(description = "наименование карты")
    private CardName name;
}
