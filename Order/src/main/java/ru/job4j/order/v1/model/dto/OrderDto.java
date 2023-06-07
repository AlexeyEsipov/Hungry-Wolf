package ru.job4j.order.v1.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@Builder
@Schema(description = "Информация о заказе")
public class OrderDto {
    @NotNull
    @Min(1)
    @Schema(description = "Идентификационный номер заказа")
    private int id;
    @NotNull
    @Schema(description = "Идентификационный номер пользователя")
    private int customerId;
    @NotNull
    @Schema(description = "Заказанные продукты - Наименование и количество")
    private Map<String, Integer> productMap;
}
