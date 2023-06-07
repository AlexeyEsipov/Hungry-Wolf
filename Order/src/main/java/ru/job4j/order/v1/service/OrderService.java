package ru.job4j.order.v1.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.order.v1.model.dto.OrderDto;
import ru.job4j.order.v1.model.entity.Customer;
import ru.job4j.order.v1.model.entity.Order;
import ru.job4j.order.v1.model.mappers.OrderMapper;
import ru.job4j.order.v1.model.mappers.OrderToNotice;
import ru.job4j.order.v1.model.status.OrderStatus;
import ru.job4j.order.v1.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderService {
    @Value("${messenger}")
    private String messenger;
    @Value("${order-to-kitchen}")
    private String toKitchen;
    @Value("${kitchen-to-order}")
    private static final String FROM_KITCHEN = "notice_kitchen";
    private final OrderRepository orderRepository;
    private final MenuService menuService;
    private final OrderMapper orderMapper;
    private final CustomerService customerService;
    private static final String ORDER_NOT_FOUND = "This OrderId was not found: ";
    private static final String CUSTOMER_NOT_FOUND = "This CustomerId was not found: ";
    private static final String DISH_NOT_FOUND = "This Dish Name was not found: ";
    private static final Gson GSON = new GsonBuilder().create();

    private final OrderToNotice orderToNotice;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderDto createOrder(OrderDto orderDto) {
        int customerId = orderDto.getCustomerId();
        if (!customerService.isExistById(customerId)) {
            throw new IllegalArgumentException("%s%s".formatted(CUSTOMER_NOT_FOUND, customerId));
        }
        Customer customer = customerService.findCustomerById(customerId);
        Set<String> setDishes = orderDto.getProductMap().keySet();
        for (String dishName : setDishes) {
            if (!menuService.isAvailableByName(dishName)) {
                throw new IllegalArgumentException("%s%s".formatted(DISH_NOT_FOUND, dishName));
            }
        }
        Order order = orderMapper.newOrderFromDTO(orderDto, customer);
        orderRepository.save(order);
        OrderDto result = orderMapper.dtoFromOrder(order);
        sendNotice(messenger, GSON.toJson(orderToNotice.toNotice(order)));
        sendNotice(toKitchen, GSON.toJson(result));
        return result;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public OrderDto updateOrder(OrderDto order) {
        if (orderRepository.existsById(order.getId())) {
            throw new IllegalArgumentException(
                    "%s%s".formatted(ORDER_NOT_FOUND, order.getId()));
        }
        Order orderExist = new Order();
        Optional<Order> optionalOrder = orderRepository.findById(order.getId());
        if (optionalOrder.isPresent()) {
            orderExist = optionalOrder.get();
        }
        orderExist.setDishes(order.getProductMap());
        orderRepository.save(orderExist);
        return orderMapper.dtoFromOrder(orderExist);
    }

    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    public List<Order> findOrdersByCustomerId(int customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> findOrdersByStatus(OrderStatus status) {
        return orderRepository.findByOrderStatus(status);
    }

    public OrderStatus getStatus(int orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException(
                "%s%s".formatted(ORDER_NOT_FOUND, orderId))).getOrderStatus();
    }

    public Order setStatus(int orderId, OrderStatus status) {
        if (orderRepository.existsById(orderId)) {
            throw new IllegalArgumentException(
                    "%s%s".formatted(ORDER_NOT_FOUND, orderId));
        }
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalArgumentException("%s%s".formatted(ORDER_NOT_FOUND, orderId)));
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    public boolean deleteOrder(int id) {
        orderRepository.deleteOrderById(id);
        return !orderRepository.existsById(id);
    }

    public void deleteByCustomerId(Customer customer) {
        orderRepository.deleteOrderByCustomer(customer);
    }

    public void sendNotice(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    @KafkaListener(topics = FROM_KITCHEN)
    public void fromKitchen(String message) {
        JSONObject object = new JSONObject(message);
        int orderId = (Integer) object.get("id");
        OrderStatus status = OrderStatus.valueOf((String) object.get("status"));
        Order order = setStatus(orderId, status);
        sendNotice(messenger, GSON.toJson(orderToNotice.toNotice(order)));
    }
}
