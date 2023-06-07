package ru.job4j.kitchen.mapper;

import org.springframework.stereotype.Component;
import ru.job4j.kitchen.dto.OrderDto;
import ru.job4j.kitchen.dto.NoticeOrder;
import ru.job4j.kitchen.model.OrderStatus;
@Component
public class OrderToNotice {
    public NoticeOrder toNotice(OrderDto dto, OrderStatus status) {
        return NoticeOrder.builder()
                .id(dto.getOrderId())
                .status(status)
                .build();
    }
}
