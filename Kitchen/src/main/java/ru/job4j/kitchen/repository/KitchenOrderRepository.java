package ru.job4j.kitchen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.kitchen.model.KitchenOrder;

@Repository
public interface KitchenOrderRepository extends CrudRepository<KitchenOrder, Integer> {
}