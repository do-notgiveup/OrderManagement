package vn.edu.likelion.OrderManagement.service;

import org.springframework.data.domain.Page;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.model.DishDTO;
import vn.edu.likelion.OrderManagement.model.TopSellingDishDTO;

import java.util.List;

public interface DishService extends BaseCRUD<DishEntity> {
//    List<DishEntity> getDishesByCategory(int categoryId);   // Get dish by Category
//    List<DishEntity> searchDishes(String keyword);  // Get dish by name or description
//    List<DishEntity> getTopSellingDishes(); // Get top seller

    DishDTO createDish(DishEntity dishEntity);
    Page<DishDTO> findAllDishes(int page, int size, String sortBy, String sortDirection, int category);
    DishDTO findByDishId(int id);
    List<DishDTO> getDishesByCategory(int categoryId);

    List<DishDTO> searchDishes(String keyword);
    List<TopSellingDishDTO> getTopSellingDishes();
}
