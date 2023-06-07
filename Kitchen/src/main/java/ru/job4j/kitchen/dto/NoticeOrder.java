package ru.job4j.kitchen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.kitchen.model.OrderStatus;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeOrder {
    private int id;
    OrderStatus status;
}
