package ru.job4j.order.v1.model.status;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Статус заказа")
public enum OrderStatus {
    @Schema(description = "статус")
    CREATED, PAID, CANCELED, READY, DELIVERY, FINISHED
}
