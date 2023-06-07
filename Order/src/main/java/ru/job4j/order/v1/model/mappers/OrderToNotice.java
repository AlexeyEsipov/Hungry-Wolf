package ru.job4j.order.v1.model.mappers;

import org.springframework.stereotype.Component;
import ru.job4j.order.v1.model.dto.Notice;
import ru.job4j.order.v1.model.entity.Order;

@Component
public class OrderToNotice {
    public Notice toNotice(Order order) {
        return Notice.builder()
                .customerId(order.getCustomer().getId())
                .email(order.getCustomer().getEmail())
                .orderId(order.getId())
                .status(order.getOrderStatus().name())
                .build();
    }
}
