package ru.job4j.order.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.order.v1.model.entity.Customer;
import ru.job4j.order.v1.model.status.CustomerStatus;
import ru.job4j.order.v1.repository.CustomerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private static final String NOT_FOUND = "This CustomerId was not found: ";
    private final CustomerRepository repository;

    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> findAllCustomer() {
        return repository.findAll();
    }

    public boolean isExistById(int id) {
        return repository.existsById(id);
    }

    public Customer findCustomerById(int id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "%s%s".formatted(NOT_FOUND, id)));
    }

    public Customer updateCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Customer setStatus(Customer customer, CustomerStatus status) {
        if (!repository.existsById(customer.getId())) {
            throw new IllegalArgumentException(
                    "%s%s".formatted(NOT_FOUND, customer.getId()));
        }
        customer.setStatus(status);
        return repository.save(customer);
    }

    public CustomerStatus getStatusById(int id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "%s%s".formatted(NOT_FOUND, id))).getStatus();
    }

    public boolean deleteCustomer(Customer customer) {
        repository.deleteById(customer.getId());
        return !repository.existsById(customer.getId());
    }
}
