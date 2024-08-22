package vn.edu.likelion.OrderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.edu.likelion.OrderManagement.entity.CategoryEntity;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.model.CreateDish;
import vn.edu.likelion.OrderManagement.model.DishDTO;
import vn.edu.likelion.OrderManagement.model.TopSellingDishDTO;
import vn.edu.likelion.OrderManagement.service.CategoryService;
import vn.edu.likelion.OrderManagement.service.DishService;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth/dishes")
public class DishController {

    // Injection
    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;

    // Get All Dishes - Pageable
    @GetMapping
    public ResponseEntity<Page<DishDTO>> getAllDishes(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "5") int size,
                                                      @RequestParam(defaultValue = "id") String sortBy,
                                                      @RequestParam(defaultValue = "asc") String sortDirection,
                                                      @RequestParam(defaultValue = "0") int category) {
        Page<DishDTO> dishes = dishService.findAllDishes(page, size, sortBy, sortDirection, category);
        return ResponseEntity.ok(dishes);
    }

    // Get Dishes via Id
    @GetMapping("/{id}")
    public ResponseEntity<DishDTO> getDishById(@PathVariable int id) {
        DishDTO dish = dishService.findByDishId(id);
        if (dish == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish not found");
        }
        return ResponseEntity.ok(dish);
    }

    // Create Dish
    @PostMapping
    public ResponseEntity<DishDTO> createDish(@RequestBody CreateDish dish) {
        CategoryEntity category = categoryService.findById(dish.getCategory_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + dish.getCategory_id()));

        try {
            DishEntity dishEntity = DishEntity.builder()
                    .name(dish.getName())
                    .description(dish.getDescription())
                    .price(Double.parseDouble(dish.getPrice()))
                    .image(dish.getImage())
                    .status(dish.isStatus())
                    .category(category)
                    .build();

            DishDTO createdDishDTO = dishService.createDish(dishEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDishDTO);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid price format");
        }
    }

    // Update Dish
    @PutMapping("/{id}")
    public ResponseEntity<DishDTO> updateDish(@PathVariable int id, @RequestBody DishEntity dish) {
        dish.setId(id);
        dish.setCategory(dishService.findById(id).get().getCategory());
        DishDTO updatedDish = dishService.updateDish(dish);
        return ResponseEntity.ok(updatedDish);
    }

    // Delete Dish
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
    public ResponseEntity<List<DishDTO>> getDishesByCategory(@PathVariable int categoryId) {
        List<DishDTO> dishes = dishService.getDishesByCategory(categoryId);
        return ResponseEntity.ok(dishes);
    }

    // Get dish by name or description
    @GetMapping("/search")
    public ResponseEntity<List<DishDTO>> searchDishes(@RequestParam String keyword) {
        List<DishDTO> dishes = dishService.searchDishes(keyword);
        return ResponseEntity.ok(dishes);
    }

    // Get top seller
    @GetMapping("/topselling")
    public ResponseEntity<List<TopSellingDishDTO>> getTopSellingDishes() {
        List<TopSellingDishDTO> dishes = dishService.getTopSellingDishes();
        return ResponseEntity.ok(dishes);
    }

}