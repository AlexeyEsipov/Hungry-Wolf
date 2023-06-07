package ru.job4j.order.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.order.v1.model.entity.Card;
import ru.job4j.order.v1.model.entity.Order;
import ru.job4j.order.v1.model.status.OrderStatus;
import ru.job4j.order.v1.service.CardService;
import ru.job4j.order.v1.service.OrderService;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/manager",
        produces = "application/json")
@Tag(
        name = "Менеджер",
        description = "Все методы для работы менеджера системы"
)
@AllArgsConstructor
public class ManagerController {
    private final OrderService orderService;
    private final CardService cardService;


    @GetMapping("/cards")
    @Operation(summary = "Список всех карт в системе")
    public ResponseEntity<List<Card>> getCards() {
        return new ResponseEntity<>(cardService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/orders")
    @Operation(summary = "Список всех заказов")
    public ResponseEntity<List<Order>> allOrders() {
        return new ResponseEntity<>(orderService.findAllOrder(), HttpStatus.OK);
    }

    @GetMapping("/finished")
    @Operation(summary = "Список всех выполненных заказов")
    public ResponseEntity<List<Order>> allFinishedOrders() {
        return new ResponseEntity<>(orderService.findOrdersByStatus(OrderStatus.FINISHED), HttpStatus.OK);
    }

    @GetMapping("/ready")
    @Operation(summary = "Список всех готовых заказов")
    public ResponseEntity<List<Order>> allReadyOrders() {
        return new ResponseEntity<>(orderService.findOrdersByStatus(OrderStatus.READY), HttpStatus.OK);
    }
}
