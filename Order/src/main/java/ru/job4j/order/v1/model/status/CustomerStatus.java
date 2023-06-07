package ru.job4j.order.v1.model.status;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Статус пользователя")
public enum CustomerStatus {
    @Schema(description = "статус")
    ACTIVE, NOT_ACTIVE
}
