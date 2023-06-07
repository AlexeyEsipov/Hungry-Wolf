package ru.job4j.order.v1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import ru.job4j.order.v1.model.entity.Customer;
import ru.job4j.order.v1.model.entity.Order;
import ru.job4j.order.v1.model.status.OrderStatus;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findByOrderStatus(@NonNull OrderStatus orderStatus);
    List<Order> findByCustomerId(@NonNull int id);
    void deleteAllByCustomer(Customer customer);
    void deleteOrderByCustomer(Customer customer);
    List<Order> findAll();
    void deleteOrderById(int id);
}
