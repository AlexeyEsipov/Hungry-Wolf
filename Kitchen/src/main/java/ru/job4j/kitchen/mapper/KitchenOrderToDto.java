package ru.job4j.kitchen.mapper;

import org.springframework.stereotype.Component;
import ru.job4j.kitchen.dto.OrderDto;
import ru.job4j.kitchen.model.KitchenOrder;
import ru.job4j.kitchen.model.OrderStatus;

@Component
public class KitchenOrderToDto {
    public OrderDto toDto(KitchenOrder order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .dishes(order.getDishes())
                .build();
    }

    public KitchenOrder toEntity(OrderDto dto) {
        return KitchenOrder.builder()
                .id(0)
                .orderId(dto.getOrderId())
                .dishes(dto.getDishes())
                .orderStatus(OrderStatus.CREATED)
                .build();
    }
}
