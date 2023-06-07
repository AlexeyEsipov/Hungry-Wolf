package ru.job4j.order.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.order.v1.model.dto.CustomerDto;
import ru.job4j.order.v1.model.dto.DishDto;
import ru.job4j.order.v1.model.dto.OrderDto;
import ru.job4j.order.v1.model.entity.Card;
import ru.job4j.order.v1.model.entity.Customer;
import ru.job4j.order.v1.model.entity.Order;
import ru.job4j.order.v1.model.mappers.CustomerMapper;
import ru.job4j.order.v1.model.status.CustomerStatus;
import ru.job4j.order.v1.model.status.MenuStatus;
import ru.job4j.order.v1.model.status.OrderStatus;
import ru.job4j.order.v1.service.CardService;
import ru.job4j.order.v1.service.CustomerService;
import ru.job4j.order.v1.service.MenuService;
import ru.job4j.order.v1.service.OrderService;

import java.util.List;

/* документация localhost:8081/swagger-ui/index.html */
@RestController
@RequestMapping(value = "/v1/customer",
        produces = "application/json")
@Tag(
        name = "Пользователи",
        description = "Все методы для работы с пользователями системы"
)
@AllArgsConstructor
public class CustomerController {
    private final CardService cardService;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final MenuService menuService;
    private final CustomerMapper customerMapper;

    @GetMapping("/orders/{customerId}")
    @Operation(summary = "Вернуть список заказов пользователя по id")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@Parameter(description = "id пользователя") @PathVariable("customerId") int id) {
        if (!customerService.isExistById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderService.findOrdersByCustomerId(id), HttpStatus.OK);
    }

    @PostMapping()
    @Operation(summary = "Создать пользователя")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = Customer.builder().id(0)
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .status(CustomerStatus.NOT_ACTIVE)
                .build();
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.CREATED);
    }

    @GetMapping("/status/{customerId}")
    @Operation(summary = "Сообщить текущий статус пользователя по его id")
    public ResponseEntity<CustomerStatus> statusCustomer(@Parameter(description = "id пользователя")
                                                             @PathVariable("customerId") int id) {
        if (!customerService.isExistById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerService.getStatusById(id), HttpStatus.OK);
    }

    @PostMapping("/card/{customerId}")
    @Operation(summary = "Создать карту для пользователя по его id")
    public ResponseEntity<Card> createCardForCustomer(@Parameter(description = "id пользователя")
                                                          @PathVariable("customerId") int id) {
        if (!customerService.isExistById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(cardService.create(customer), HttpStatus.CREATED);
    }

    @PostMapping("orders/{customerId}/create")
    @Operation(summary = "Создать заказ для пользователя с Id")
    public ResponseEntity<OrderDto> createOrder(@PathVariable("customerId") int id, @RequestBody OrderDto dto) {
        dto.setCustomerId(id);
        return new ResponseEntity<>(orderService.createOrder(dto), HttpStatus.CREATED);
    }

    @GetMapping("orders/{customerId}/status/{orderId}")
    @Operation(summary = "Сообщить статус ордера orderId для пользователя с Id")
    public ResponseEntity<OrderStatus> getStatusOrder(@PathVariable("customerId") int customerId, @PathVariable("orderId") int orderId) {
        return new ResponseEntity<>(orderService.getStatus(orderId), HttpStatus.OK);
    }

    @PutMapping("orders/{customerId}/cancel/{orderId}")
    @Operation(summary = "Отменить ордер orderId для пользователя с Id")
    public ResponseEntity<Order> cancelOrder(@PathVariable("customerId") int customerId, @PathVariable("orderId") int orderId) {
        return new ResponseEntity<>(orderService.setStatus(orderId, OrderStatus.CANCELED), HttpStatus.OK);
    }

    @PatchMapping("orders/{customerId}/change")
    @Operation(summary = "Изменить ордер orderId для пользователя с Id")
    public ResponseEntity<OrderDto> changeOrder(@PathVariable("customerId") int customerId, @RequestBody OrderDto dto) {
        return new ResponseEntity<>(orderService.updateOrder(dto), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Список продуктов, доступных для заказа")
    public ResponseEntity<List<DishDto>> getAvailableMenu() {
        List<DishDto> menu = menuService.findByStatus(MenuStatus.AVAILABLE);
        if (menu.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }
}
