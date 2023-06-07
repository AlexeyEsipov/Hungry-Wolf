package ru.job4j.dish.v1.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Schema(description = "Информация о блюде")
@Data
public class DishDto {
    @Schema(description = "Наименование")
    @NotNull
    private String name;
    @Schema(description = "Цена")
    @Positive
    private double price;
}