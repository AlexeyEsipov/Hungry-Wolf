package ru.job4j.dish.v1.model.mappers;

import org.mapstruct.Mapper;
import ru.job4j.dish.v1.model.Dish;
import ru.job4j.dish.v1.model.dto.DishDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DishMapper {
    DishDto toDto(Dish dish);
    List<DishDto> toDto(List<Dish> dishes);
}