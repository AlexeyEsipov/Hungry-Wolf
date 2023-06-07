package ru.job4j.order.v1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import ru.job4j.order.v1.model.entity.Card;
import ru.job4j.order.v1.model.entity.Customer;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Integer> {
    long deleteByCustomerId(@NonNull Customer customerId);
    List<Card> findByCustomerIdId(@NonNull int id);
}