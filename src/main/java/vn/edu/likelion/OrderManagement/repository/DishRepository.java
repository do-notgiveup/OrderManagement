package vn.edu.likelion.OrderManagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.OrderManagement.entity.DishEntity;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Integer> {

    List<DishEntity> findByCategoryId(Integer categoryId);

    List<DishEntity> findByStatus(boolean status);

    List<DishEntity> findByPriceBetween(double minPrice, double maxPrice);

    List<DishEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nameKeyword, String descriptionKeyword);

//    @Query("SELECT *" +
//            "FROM BOOKS" +
//            "ORDER BY ID" +
//            "OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY;")
    Page<DishEntity> findAll(Pageable pageable);

    Page<DishEntity> findByIsDeleted(Pageable pageable, boolean isDeleted);

    Page<DishEntity> findByIsDeletedAndCategoryId(Pageable pageable, boolean isDeleted, int category);

}
