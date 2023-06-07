package ru.job4j.order.v1.model.mappers;

import org.springframework.stereotype.Component;
import ru.job4j.order.v1.model.dto.OrderDto;
import ru.job4j.order.v1.model.entity.Customer;
import ru.job4j.order.v1.model.entity.Order;
import ru.job4j.order.v1.model.status.OrderStatus;

import java.util.Date;

@Component
public class OrderMapper {
    public Order newOrderFromDTO(OrderDto orderDto, Customer customer) {
        return Order.builder()
                .customer(customer)
                .created(new Date())
                .modify(new Date())
                .dishes(orderDto.getProductMap())
                .orderStatus(OrderStatus.CREATED)
                .build();
    }

    public OrderDto dtoFromOrder(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .productMap(order.getDishes())
                .build();
    }
}
