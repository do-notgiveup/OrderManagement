package vn.edu.likelion.OrderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.edu.likelion.OrderManagement.entity.CategoryEntity;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.model.CreateDish;
import vn.edu.likelion.OrderManagement.service.CategoryService;
import vn.edu.likelion.OrderManagement.service.DishService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth/dishes")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<DishEntity>> getAllDishes() {
        List<DishEntity> dishes = dishService.findAll();
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishEntity> getDishById(@PathVariable int id) {
        DishEntity dish = dishService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(dish);
    }

    @PostMapping
    public ResponseEntity<DishEntity> createDish(@RequestBody CreateDish dish) {
        CategoryEntity category = categoryService.findById(dish.getCategory_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")); // Check category

        DishEntity dishEntity = new DishEntity();
        dishEntity.setName(dish.getName());
        dishEntity.setDescription(dish.getDescription());
        dishEntity.setPrice(Double.parseDouble(dish.getPrice()));
        dishEntity.setImage(dish.getImage());
        dishEntity.setStatus(dish.isStatus());
        dishEntity.setCategory(category);
        DishEntity createdDish = dishService.create(dishEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDish);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishEntity> updateDish(@PathVariable int id, @RequestBody DishEntity dish) {
        dish.setId(id);
        DishEntity updatedDish = dishService.update(dish);
        return ResponseEntity.ok(updatedDish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable int id) {
        Optional<DishEntity> dishOptional = dishService.findById(id);
        if (dishOptional.isPresent()) {
            dishService.delete(dishOptional.get());
            return ResponseEntity.ok("Dish deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get dish by Category
    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<DishEntity>> getDishesByCategory(@PathVariable int categoryId) {
        List<DishEntity> dishes = dishService.getDishesByCategory(categoryId);
        return ResponseEntity.ok(dishes);
    }

    // Get dish by name or description
    @GetMapping("/search")
    public ResponseEntity<List<DishEntity>> searchDishes(@RequestParam String keyword) {
        List<DishEntity> dishes = dishService.searchDishes(keyword);
        return ResponseEntity.ok(dishes);
    }

    // Get top seller
    @GetMapping("/top-selling")
    public ResponseEntity<List<DishEntity>> getTopSellingDishes() {
        List<DishEntity> dishes = dishService.getTopSellingDishes();
        return ResponseEntity.ok(dishes);
    }

}