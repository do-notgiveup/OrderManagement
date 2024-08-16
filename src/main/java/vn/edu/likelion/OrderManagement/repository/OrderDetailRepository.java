package vn.edu.likelion.OrderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.OrderManagement.entity.OrderDetailEntity;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {

    List<OrderDetailEntity> findByOrderId(Integer orderId);

    List<OrderDetailEntity> findByDishId(Integer dishId);
}
