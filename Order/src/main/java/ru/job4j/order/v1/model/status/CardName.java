package ru.job4j.order.v1.model.status;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Карта лояльности")
public enum CardName {
    @Schema(description = "наименование")
    REGULAR(3), GOLD(5);
    @Schema(description = "скидка в процентах")
    int rate;

    CardName(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
