package vn.edu.likelion.OrderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.OrderManagement.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    List<OrderEntity> findByTableId(Integer tableId);

    List<OrderEntity> findByUserId(Integer userId);

    List<OrderEntity> findByStatus(boolean status);

    //List<OrderEntity> findByTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
}
