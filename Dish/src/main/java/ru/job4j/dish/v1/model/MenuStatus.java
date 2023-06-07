package ru.job4j.dish.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Доступность блюда")
public enum MenuStatus {
    @Schema(description = "статус")
    AVAILABLE, NOT_AVAILABLE
}
