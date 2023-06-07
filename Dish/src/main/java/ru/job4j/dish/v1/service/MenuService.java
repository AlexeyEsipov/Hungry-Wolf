package ru.job4j.dish.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.dish.v1.model.Dish;
import ru.job4j.dish.v1.model.MenuStatus;
import ru.job4j.dish.v1.repository.DishRepository;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MenuService {
    private final DishRepository dishRepository;

    public List<Dish> getAll() {
        return (List<Dish>) dishRepository.findAll();
    }

    public Dish create(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish findById(int id) {
        return dishRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "This Dish ID was not found: %s".formatted(id)));
    }

    public Dish findByName(String name) {
        return dishRepository.findByNameIgnoreCase(name).orElseThrow(() -> new IllegalArgumentException(
                "This Dish Name was not found: %s".formatted(name)));
    }

    public boolean isAvailableByName(String name) {
        return dishRepository.existsByNameIgnoreCaseAndStatus(name, MenuStatus.AVAILABLE);
    }

    public List<Dish> findByStatus(MenuStatus status) {
        return dishRepository.findByStatus(status);
    }

    public Set<Dish> findSetNames(Set<String> setNames) {
        return dishRepository.findByNameInIgnoreCaseAndStatus(setNames, MenuStatus.AVAILABLE);
    }

    public void deleteById(int id) {
        dishRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        dishRepository.deleteByNameIgnoreCase(name);
    }
}
