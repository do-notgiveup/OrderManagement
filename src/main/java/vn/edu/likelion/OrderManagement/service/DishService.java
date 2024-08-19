package vn.edu.likelion.OrderManagement.service;

import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.model.DishDTO;

import java.util.List;
import java.util.Optional;

public interface DishService extends BaseCRUD<DishEntity> {
//    List<DishEntity> getDishesByCategory(int categoryId);   // Get dish by Category
//    List<DishEntity> searchDishes(String keyword);  // Get dish by name or description
//    List<DishEntity> getTopSellingDishes(); // Get top seller

    DishDTO createDish(DishEntity dishEntity);
    List<DishDTO> findAllDishes();
    DishDTO findByDishId(int id);
    List<DishDTO> getDishesByCategory(int categoryId);

    List<DishDTO> searchDishes(String keyword);
    List<DishDTO> getTopSellingDishes();
}
