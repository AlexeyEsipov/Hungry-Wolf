package ru.job4j.order.v1.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.order.v1.model.entity.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    List<Customer> findAll();
}
