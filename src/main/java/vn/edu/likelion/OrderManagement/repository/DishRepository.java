package vn.edu.likelion.OrderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.OrderManagement.entity.DishEntity;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Integer> {

    List<DishEntity> findByCategoryId(Integer categoryId);

//    DishEntity findByNameIgnoreCase(String name);

    List<DishEntity> findByStatus(boolean status);

    List<DishEntity> findByPriceBetween(double minPrice, double maxPrice);

    List<DishEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nameKeyword, String descriptionKeyword);
}
