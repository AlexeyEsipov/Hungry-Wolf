package ru.job4j.dish.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dish.v1.model.Dish;
import ru.job4j.dish.v1.model.MenuStatus;
import ru.job4j.dish.v1.model.dto.DishDto;
import ru.job4j.dish.v1.model.mappers.DishMapper;
import ru.job4j.dish.v1.service.MenuService;

import java.util.List;
/* документация localhost:8082/swagger-ui/index.html */
@RestController
@RequestMapping(value = "/v1/menu",
        produces = "application/json")
@Tag(
        name = "Меню для заказа блюд",
        description = "Все методы для работы с меню"
)
@AllArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final DishMapper dishMapper;

    @GetMapping
    @Operation(summary = "Список продуктов, доступных для заказа")
    public ResponseEntity<List<DishDto>> getAvailableMenu() {
        List<Dish> menu = menuService.findByStatus(MenuStatus.AVAILABLE);
        if (menu.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishMapper.toDto(menu), HttpStatus.OK);
    }

    @GetMapping("/available")
    @Operation(summary = "Список продуктов, доступных для заказа (Dto)")
    public List<DishDto> getAvailableMenuDto() {
        List<Dish> menu = menuService.findByStatus(MenuStatus.AVAILABLE);
        return dishMapper.toDto(menu);
    }

    @PostMapping("/adddish")
    @Operation(summary = "Добавить блюдо в меню")
    public ResponseEntity<DishDto> addDish(@RequestBody Dish dish) {
        return new ResponseEntity<>(dishMapper.toDto(menuService.create(dish)), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "Список блюд с любым статусом")
    public ResponseEntity<List<Dish>> getAllMenu() {
        List<Dish> menu = menuService.getAll();
        if (menu.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    @Operation(summary = "Удалить блюдо")
    public void deleteProductById(@Parameter(description = "id блюда")@PathVariable("productId") int id) {
        menuService.deleteById(id);
    }

    @GetMapping("/exist/{name}")
    public boolean existsByNameIgnoreCase(@PathVariable("name") String name) {
        return menuService.isAvailableByName(name);
    }
}
