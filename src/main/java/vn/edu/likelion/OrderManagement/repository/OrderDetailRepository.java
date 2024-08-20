package vn.edu.likelion.OrderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.OrderManagement.entity.OrderDetailEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {

    List<OrderDetailEntity> findByOrderId(Integer orderId);

    List<OrderDetailEntity> findByDishId(Integer dishId);

    @Query("SELECT od.dish, SUM(od.quantity) " +
            "FROM OrderDetailEntity od " +
            "WHERE od.order.createTime BETWEEN :startTime AND :endTime " +
            "GROUP BY od.dish " +
            "ORDER BY SUM(od.quantity) DESC")
    List<Object[]> findTopSellingDishes(LocalDate startTime, LocalDate endTime);
}
