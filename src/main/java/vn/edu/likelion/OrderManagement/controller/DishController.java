package vn.edu.likelion.OrderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.service.DishService;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

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
    public ResponseEntity<DishEntity> createDish(@RequestBody DishEntity dish) {
        DishEntity createdDish = dishService.create(dish);
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
        DishEntity dish = new DishEntity();
        dish.setId(id);
        boolean deleted = dishService.delete(dish);
        if (deleted) {
            //return ResponseEntity.noContent().build();
            return ResponseEntity.ok("Dish deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get dish by Câtgory
    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<DishEntity>> getDishesByCategory(@PathVariable Integer categoryId) {
        List<DishEntity> dishes = dishService.getDishesByCategory(categoryId);
        return ResponseEntity.ok(dishes);
    }
}