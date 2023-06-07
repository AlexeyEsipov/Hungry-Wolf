package ru.job4j.order.v1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

    private int id;

    private int customerId;

    private String email;

    private int orderId;

    private String status;
}
