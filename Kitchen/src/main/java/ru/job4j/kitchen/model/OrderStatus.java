package ru.job4j.kitchen.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Статус заказа")
public enum OrderStatus {
    @Schema(description = "статус")
    CREATED, PAID, CANCELED, READY, DELIVERY, FINISHED
}
